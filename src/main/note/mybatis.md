
#### 0. JDBC
> 1. ע�����ݿ������࣬��ȷָ�����ݿ�URL��ַ�����ݿ��û����������������Ϣ
> 2. DiverManager �����ݿ�����
> 3. ͨ�����ݿ����ӴӴ��� Statement(PreparedStatement)
> 4. ִ��SQL,�õ�ResultSet
> 5. ͨ��Result��ȡ���ݣ���ת����JavaBean
> 6. �ر���Դ


#### 1. ���Ĵ����

##### 1.1 ��������
`mapper.xml` �� `mybatis-config.xml` �������Ķ��� `Configuration` ���󡣺����� XMLConfigBuilder   

**properties**


**settings**


**typeAliases**


**typeHandlers**


**plugins**
ͨ��Configuration#InterceptorChain������ʵ�־���һ��ArrayList

**mappers**
1. ���� SqlSessionFactory 

##### 1.2 ����ӳ���ļ�
������ XMLMapperBuilder# configurationElement(XNode context)
1. ��ȡ�����ռ�namespace�����õ� builderAssistant ��
2. cache-ref ��ǩ����
3. cache ��ǩ ��������
    ```xml
     <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="false" type="PERPETUAL" blocking="false"/>
    ```
      1. ��ʼ�� CacheBuilder. new CacheBuilder(currentNamespace).build()
         1. setDefaultImplementations() ����Ĭ�ϻ�������(PerpetualCache)�ͻ���װ����decorators(LruCache)����
         2. Cache = newBaseCacheInstance(implementation, id) ����class����constructor��newInstance��������
         3. setStandardDecorators(cache) ���ñ�׼װ����
             > 1. LoggingCache& SynchronizedCache
             > 2. clearInterval��ScheduledCache
             > 3. readWrite��SerializedCache
             > 4. blocking��BlockingCache
      2. configuration.addCache(cache)
  
4. resultMap ��ǩ
   configuration.addResultMap(resultMap) ==  resultMaps.put(rm.getId(), rm); 

5. sql ��ǩ
6. select|insert|update|delete ��ǩ
7. ����ģʽ���� MapperBuilderAssistant# currentCache
```java
void useNewCache(Class<? extends Cache> typeClass, Class<? extends Cache> evictionClass, Long flushInterval, Integer size, boolean readWrite,
boolean blocking,Properties props)
```
8. ����namespace��mapper�� 
`
<!-- type:interface UserMapper -->
knownMappers.put(type, new MapperProxyFactory<T>(type)); 
`
9. SQL xml����
�ڻ�ȡxml�����ļ�����ΪSQL�Ĺ���ʱ��` BoundSql boundSql = sqlSource.getBoundSql(parameterObject); `��ȡ����ʵ������Ҫ��
- DynamicSqlSource, ������SQLע����${}ռλ�������� <if>��<where> �ȱ�ǩʱ
- RawSqlSource
- StaticSqlSource, new BoundSql
- ProviderSqlSource
- VelocitySqlSource
##### 1.3 ����ӳ��


##### 1.4 SQL����


##### 1.5 SQLִ��


##### 1.6 �����ӳ��


##### 1.7 ���


#### 2. ����֧�ֲ�

##### 2.1 ����ģ��

##### 2.2 ����ת��ģ��
�̳� `BaseTypeHandler`������ `setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType)`

**����ע��**
`@MappedJdbcTypes` �� `@MappedTypes` ��������ص�handler
ά����TYPE_HANDLER_MAP��¼��Java������ָ��JavaTypeת��ʱ��Ҫʹ�õ�TypeHandler������Java�����е�String����ת�������ݿ��char��varchar���Դ���һ�Զ��ϵ

**ɨ��ע��**


**����TypeHandler**


##### 2.3 ��־ģ��
   > ������ģʽ����Ҫ�ǽ�����ڽӿڲ��ܼ��ݶ��������޷�ʹ�õ�����
   
   
##### 2.4 ��Դģ�飨����أ�


##### 2.5 DataSource
   > ����ģʽ����ô����չ DataSource?
   > ����ģʽ���� PooledConnection ���� Connection �����м��checkConnection�����Ƿ��жϣ�
 
 
##### 2.6 Transactionģ�� �����ݿ�������г���
>��JDBCTransaction: connection, dataSource, transactionIsolationLevel, autoCommit


##### 2.7 bindingģ��
> ��ѯ�����ʱ����Ҫʹ��SqlSession.queryForObject('selectObject',id),ǰ��Ĳ���ָ����ִ��SQL����id��SQLӳ���������ļ��С���bindingģ�������Mapper�ڳ�ʼ���׶β�������������ûƥ���ϵĽӿ�

**MapperRegistry**

**MapperRegistryFactory**

**MapperFactory**

**MapperMethod**
`SqlCommand` nameִ�з���&type��������  
`MethodSignature` �������� 
> ParamNameResolver�����������¼��sortedMap<Integer,String> ��RowBounds��ResultHandler����ע��


##### 2.8 ����ģ��
**����Cache**
�ڱ�װ����PerpetualCacheά���Ż���ͬʱ��***Cache����װ��(���Ƕ��̳���Cache)
> LruCache �������ʹ�á�ÿ��putObject���eldestKey���ܿ����Ƴ���װ���ߵĻ������ݣ�ͬʱ��getObjectʱ�����LruCache����˳�� ��дLinkedHashMap accessOrder=true
> SoftCache ������ SoftReference����������ڴ治��ʱ������ⲿ�������ڿ���ͨ��������ʽ�ָ��Ļ��档ÿ�δ�Ż�����Ѿ�������Ķ������ݾ����Ƿ���softReference��
> WeakCache ������ WeakReference����������ʱ����ա�WeakHashMap
> ScheduleCache �����Ի���
> LoggingCache ��־���档��¼Cache�����д����ͷ��ʴ���
> SynchronizedCache 

**CacheKey**


#### 3. SQLִ�й���

##### 3.1 Ϊ Mapper �ӿ�����ʵ������ִ��SQL
    DefaultSqlSession.getMapper(Class<T> type)

1. MapperProxy ���� UserMapper �ӿ�ִ��
2. ��������Ƕ����� Object ���еģ���ֱ�ӵ���
3. ��� isDefaultMethod(method)�� invokeDefaultMethod(proxy, method, args); ��� JDK 1.8 �ӿڵ�Ĭ�Ϸ����ṩ��֧��
4. �����½� MapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration()) ��װMapper�ӿڶ�Ӧ�ķ�������Ӧ��SQL��� �����뻺��
   > ParamNameResolver ����������
5. ���� execute ����ִ�� SQL mapperMethod.execute(sqlSession, args)


##### 3.2 ��ѯ���ִ�й���
1. result = sqlSession.selectOne(command.getName(), param);
2. CachingExecutor �������� Map<Cache, TransactionalCache> �������(LRU..)
3. BaseExecutor һ������ PerpetualCache# Map<Object, Object> cache ���û����ֱ�Ӳ�� queryFromDatabase()
4. SimpleExecutor# doQuery() ��ʼ��ʼ�� StatementHandler ����JDBC����Ľ���
     ```java
     public class SimpleExecutor extends BaseExecutor {
       @Override
       public <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
         Statement stmt = null;
         try {
           Configuration configuration = ms.getConfiguration();
           /** 1.RoutingStatementHandler ���� statementType ·�ɴ��� handler Ĭ�� PreparedStatementHandler(boundSql,parameterHandler..)
               ÿ�β�ѯ��new statementHandler?     **/
           StatementHandler handler = configuration.newStatementHandler(wrapper, ms, parameter, rowBounds, resultHandler, boundSql);
           // 2.��ȡ���ݿ����ӣ����� Statement������SQL����
           stmt = prepareStatement(handler, ms.getStatementLog());
           return handler.<E>query(stmt, resultHandler);
         } finally {
           closeStatement(stmt);
         }
       }
     }
     ```

5. ׼�� Statement = prepareStatement(handler, ms.getStatementLog());
   1. ��ȡ���ݿ����� Connection = getConnection(statementLog)
   > Mybatis ͨ������Դ��ȡ���ӡ�1.�ṩ�����ֻ���JDBC�ӿڵ�����Դ�ֱ�Ϊ PooledDataSource �� UnpooledDataSource������DriverManager��2.Druid
   2. Statement = handler.prepare(connection, transaction.getTimeout()); JDBC connection.prepareStatement(sql, keyColumnNames)
   3. �������õ�SQL handler.parameterize(stmt);
6. ִ�� handler.<E>query(stmt, resultHandler)

    
##### 3.3 ��������Դ���ⲿ����Դ
   > Connection = JdbcTransaction#getConnection(statementLog) ��ȡ���ݿ����ӿ�ʼ
   > ����Դ���ã�
   1. UnpooledDataSource
   2. PooledDataSource �ṩ���̳߳ط���
   
##### 3.4 ����
1. LruCache LinkedHashMap�� `accessOrder=true` ��֤��ά����ֵ�ķ���˳��ͬʱ��д `removeEldestEntry` �ڴ�С�����������ÿ��ɾ���������ʹ���Ǹ�����һ��
2. BlockingCache �������� ���� `ReentrantLock` ʵ��
    > It sets a lock over a cache key when the element is not found in cache.
    > This way, other threads will wait until this element is filled instead of hitting the database.

    �����˵�ڵ��ڻ���ָ�� key ��ӦԪ�ز������ڻ�����ʱ��BlockingCache ����� lock ���м�����
    ��ʱ�������߳̽������ȴ�״̬��ֱ���� key ��Ӧ��Ԫ�ر���䵽�����С��������������̶߳�ȥ�������ݿ�
3. CacheKey
4. һ������ PerpetualCache +�������
   > �ڽ������ݿ��ѯ֮ǰ��MyBatis ���Ȼ����Լ��������Ƿ�����Ӧ�ļ�¼�����еĻ�ֱ�ӷ��ؼ��ɡ�һ�����������ݿ�����һ����������һ������δ���У���ѯ�����䵽���ݿ���

   1. `CacheKey = createCacheKey(ms, parameter, rowBounds, boundSql);` ��ȡCacheKey
   2. `BaseExecutor# list = resultHandler == null ? (List<E>) PerpetualCache.getObject(key) : null;`

5. �������� CachingExecutor +�������TransactionalCacheManager
       > �������湹����һ������֮�ϣ����յ���ѯ����ʱ��MyBatis ���Ȼ��ѯ�������档����������δ���У���ȥ��ѯһ�����档
       > ��һ�����治ͬ����������;���������ռ�󶨣�һ���������Ǻ� SqlSession �󶨡�
       > �ڰ��� MyBatis �淶ʹ�� SqlSession ������£�һ�����治���ڲ������⡣����������Ȼ������������ڶ�������ռ�乲��
       > ��������£�����ڲ������⣬�����Ҫ�����ȥ�������˲������⣬�������滹������������

      1. `CachingExecutor# Cache = MappedStatement.getCache();` ��MappedStatement��ȡcache��ms��ȫ�ֱ���
      2. �߳����⣻��cache����ʱ����� MapperBuilderAssistant#build() -- setStandardDecorators(cache) -- cache = new SynchronizedCache(cache);
         �������⣺TransactionalCache


       > ʹ����������ԭ��































