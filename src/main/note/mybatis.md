
#### JDBC
> 1. 注册数据库驱动类，明确指定数据库URL地址、数据库用户名、密码等连接信息
> 2. DiverManager 打开数据库连接
> 3. 通过数据库连接从创建 Statement(PreparedStatement)
> 4. 执行SQL,得到ResultSet
> 5. 通过Result读取数据，并转换成JavaBean
> 6. 关闭资源

#### 配置文件解析 
核心类 XMLConfigBuilder 
<settings>, 
<typeAliases>, 
<typeHandlers>,
<mappers>

1. 构建 SqlSessionFactory 


#### 解析映射文件
核心类 XMLMapperBuilder# configurationElement(XNode context)
1. 获取命名空间namespace并设置到 builderAssistant 中
2. cache-ref 标签解析
3. cache 标签 二级缓存
    ```xml
     <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="false" type="PERPETUAL" blocking="false"/>
    ```
      1. 初始化 CacheBuilder. new CacheBuilder(currentNamespace).build()
         1. setDefaultImplementations() 设置默认缓存类型(PerpetualCache)和缓存装饰器decorators(LruCache)代理
         2. Cache = newBaseCacheInstance(implementation, id) 反射class创建constructor再newInstance创建缓存
         3. setStandardDecorators(cache) 设置标准装饰器
             > 1. LoggingCache& SynchronizedCache
             > 2. clearInterval是ScheduledCache
             > 3. readWrite是SerializedCache
             > 4. blocking是BlockingCache
      2. configuration.addCache(cache)
  
4. resultMap 标签
   configuration.addResultMap(resultMap) ==  resultMaps.put(rm.getId(), rm); 

5. sql 标签
6. select|insert|update|delete 标签
7. 建造模式构建 MapperBuilderAssistant# currentCache
```java
void useNewCache(Class<? extends Cache> typeClass, Class<? extends Cache> evictionClass, Long flushInterval, Integer size, boolean readWrite,
boolean blocking,Properties props)
```
8. 最后把namespace和mapper绑定 
`
<!-- type:interface UserMapper -->
knownMappers.put(type, new MapperProxyFactory<T>(type)); 
`
9. SQL xml解析
在获取xml配置文件解析为SQL的过程时，` BoundSql boundSql = sqlSource.getBoundSql(parameterObject); `获取，其实现类主要有
- DynamicSqlSource, 适用于SQL注入如${}占位符，包含 <if>、<where> 等标签时
- RawSqlSource
- StaticSqlSource, new BoundSql
- ProviderSqlSource
- VelocitySqlSource



#### SQL执行过程

##### 1. 为 Mapper 接口生成实现类来执行SQL
    DefaultSqlSession.getMapper(Class<T> type)

1. MapperProxy 代理 UserMapper 接口执行
2. 如果方法是定义在 Object 类中的，则直接调用
3. 如果 isDefaultMethod(method)则 invokeDefaultMethod(proxy, method, args); 这对 JDK 1.8 接口的默认方法提供了支持
4. 否则新建 MapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration()) 封装Mapper接口对应的方法及对应的SQL语句 并加入缓存
   > ParamNameResolver 反射解析入参
5. 调用 execute 方法执行 SQL mapperMethod.execute(sqlSession, args)


##### 2. 查询语句执行过程
1. result = sqlSession.selectOne(command.getName(), param);
2. CachingExecutor 二级缓存 Map<Cache, TransactionalCache> 缓存策略(LRU..)
3. BaseExecutor 一级缓存 PerpetualCache# Map<Object, Object> cache 如果没有再直接查库 queryFromDatabase()
4. SimpleExecutor# doQuery() 开始初始化 StatementHandler 进行JDBC层面的交互
     ```java
     public class SimpleExecutor extends BaseExecutor {
       @Override
       public <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
         Statement stmt = null;
         try {
           Configuration configuration = ms.getConfiguration();
           /** 1.RoutingStatementHandler 根据 statementType 路由代理 handler 默认 PreparedStatementHandler(boundSql,parameterHandler..)
               每次查询都new statementHandler?     **/
           StatementHandler handler = configuration.newStatementHandler(wrapper, ms, parameter, rowBounds, resultHandler, boundSql);
           // 2.获取数据库连接；创建 Statement；设置SQL参数
           stmt = prepareStatement(handler, ms.getStatementLog());
           return handler.<E>query(stmt, resultHandler);
         } finally {
           closeStatement(stmt);
         }
       }
     }
     ```

5. 准备 Statement = prepareStatement(handler, ms.getStatementLog());
   1. 获取数据库连接 Connection = getConnection(statementLog)
   > Mybatis 通过数据源获取连接。1.提供了两种基于JDBC接口的数据源分别为 PooledDataSource 和 UnpooledDataSource。而非DriverManager；2.Druid
   2. Statement = handler.prepare(connection, transaction.getTimeout()); JDBC connection.prepareStatement(sql, keyColumnNames)
   3. 参数设置到SQL handler.parameterize(stmt);
6. 执行 handler.<E>query(stmt, resultHandler)

    
##### 3. 内置数据源和外部数据源
   > Connection = JdbcTransaction#getConnection(statementLog) 获取数据库连接开始
   > 数据源作用：
   1. UnpooledDataSource
   2. PooledDataSource 提供了线程池服务
   
##### 4.缓存
1. LruCache LinkedHashMap的 `accessOrder=true` 保证其维护键值的访问顺序，同时重写 `removeEldestEntry` 在大小不够的情况下每次删除最近最少使用那个即第一个
2. BlockingCache 阻塞特性 基于 `ReentrantLock` 实现
    > It sets a lock over a cache key when the element is not found in cache.
    > This way, other threads will wait until this element is filled instead of hitting the database.

    这个是说在当在缓存指定 key 对应元素不存在于缓存中时，BlockingCache 会根据 lock 进行加锁。
    此时，其他线程将会进入等待状态，直到与 key 对应的元素被填充到缓存中。而不是让所有线程都去访问数据库
3. CacheKey
4. 一级缓存 PerpetualCache +缓存策略
   > 在进行数据库查询之前，MyBatis 首先会检查以及缓存中是否有相应的记录，若有的话直接返回即可。一级缓存是数据库的最后一道防护，若一级缓存未命中，查询请求将落到数据库上

   1. `CacheKey = createCacheKey(ms, parameter, rowBounds, boundSql);` 拿取CacheKey
   2. `BaseExecutor# list = resultHandler == null ? (List<E>) PerpetualCache.getObject(key) : null;`

5. 二级缓存 CachingExecutor +事务控制TransactionalCacheManager
       > 二级缓存构建在一级缓存之上，在收到查询请求时，MyBatis 首先会查询二级缓存。若二级缓存未命中，再去查询一级缓存。
       > 与一级缓存不同，二级缓存和具体的命名空间绑定，一级缓存则是和 SqlSession 绑定。
       > 在按照 MyBatis 规范使用 SqlSession 的情况下，一级缓存不存在并发问题。二级缓存则不然，二级缓存可在多个命名空间间共享。
       > 这种情况下，会存在并发问题，因此需要针对性去处理。除了并发问题，二级缓存还存在事务问题

      1. `CachingExecutor# Cache = MappedStatement.getCache();` 从MappedStatement获取cache，ms是全局变量
      2. 线程问题；在cache构建时候加锁 MapperBuilderAssistant#build() -- setStandardDecorators(cache) -- cache = new SynchronizedCache(cache);
         读脏问题：TransactionalCache


       > 使用两个缓存原因


#### 基础支持层


##### 1. 反射模块


##### 2. 类型转换模块
继承 `BaseTypeHandler`，参数 `setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType)`

**单个注册**
`@MappedJdbcTypes` 和 `@MappedTypes` 来设置相关的handler
维护在TYPE_HANDLER_MAP记录了Java类型想指定JavaType转换时需要使用的TypeHandler对象。如Java类型中的String可以转换成数据库的char及varchar所以存在一对多关系

**扫描注册**


**查找TypeHandler**



##### 3. 日志模块
   > 适配器模式：主要是解决由于接口不能兼容而导致类无法使用的问题
   
   
##### 4. 资源模块（类加载）


##### 5. DataSource
   > 工厂模式：怎么给扩展 DataSource?
   > 代理模式：（ PooledConnection 代理 Connection 过程中检查checkConnection连接是否中断）
 
 
##### 6. Transaction模块 对数据库事务进行抽象
>如JDBCTransaction: connection, dataSource, transactionIsolationLevel, autoCommit


##### 7 binding模块
> SqlCommand, name执行方法&type方法类型
> MethodSignature, 参数处理。 ParamNameResolver和也别标注rowBoundsIndex resultHandlerIndex


##### 8 缓存模块
> 装饰器模式：动态的为对象添加功能它是基于组合方式实现该功能的。以LruCache为例，在被装饰者PerpetualCache维护着缓存同时用LruCache进行装饰(他们都继承着Cache)
> 具体装饰功能是，每次putObject如果eldestKey不能空则移除被装饰者的缓存数据，同时在getObject时候更新LruCache队列顺序 accessOrder=true


#### 核心处理层


##### 1.初始化
> 建造者模式：

`mapper.xml` 和 `mybatis-config.xml` 最后解析的都是 `Configuration` 对象




























