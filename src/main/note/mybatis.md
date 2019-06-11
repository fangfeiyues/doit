
#### 1.配置文件解析 
核心类 XMLConfigBuilder 
<settings>, 
<typeAliases>, 
<typeHandlers>,
<mappers>

1. 构建 SqlSessionFactory 


#### 2.解析映射文件
核心类 XMLMapperBuilder# configurationElement(XNode context)
1. 获取命名空间namespace并设置到 builderAssistant 中
2. cache-ref 标签解析
3. cache 标签 二级缓存
```xml
 <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="false" type="PERPETUAL" blocking="false"/>
```
  1. 初始化CacheBuilder. new CacheBuilder(currentNamespace).build()
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



#### 3.SQL执行过程
1. 为 Mapper 接口生成实现类来执行SQL  DefaultSqlSession.getMapper(Class<T> type)
    1. MapperProxy 代理 UserMapper 接口执行
    2. 如果方法是定义在 Object 类中的，则直接调用
    3. 如果 isDefaultMethod(method)则 invokeDefaultMethod(proxy, method, args); 这对 JDK 1.8 接口的默认方法提供了支持
    4. 否则新建 MapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration())并加入缓存
       > ParamNameResolver 反射解析入参
    5. 调用 execute 方法执行 SQL mapperMethod.execute(sqlSession, args)

2. 查询语句执行过程
    1. result = sqlSession.selectOne(command.getName(), param); 
    2. CachingExecutor 二级缓存 Map<Cache, TransactionalCache> 缓存策略(LRU..)
    3. BaseExecutor 一级缓存 PerpetualCache# Map<Object, Object> cache 如果没有再直接查库
    4. 查询数据库queryFromDatabase()#doQuery
    
    ```java
    public class SimpleExecutor extends BaseExecutor {
      @Override
      public <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        Statement stmt = null;
        try {
          Configuration configuration = ms.getConfiguration();
          StatementHandler handler = configuration.newStatementHandler(wrapper, ms, parameter, rowBounds, resultHandler, boundSql);
          stmt = prepareStatement(handler, ms.getStatementLog());
          return handler.<E>query(stmt, resultHandler);
        } finally {
          closeStatement(stmt);
        }
      }
    }
    ```
3. 更新语句执行过程








   