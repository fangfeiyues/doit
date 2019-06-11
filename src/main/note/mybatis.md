
#### 1.�����ļ����� 
������ XMLConfigBuilder 
<settings>, 
<typeAliases>, 
<typeHandlers>,
<mappers>

1. ���� SqlSessionFactory 


#### 2.����ӳ���ļ�
������ XMLMapperBuilder# configurationElement(XNode context)
1. ��ȡ�����ռ�namespace�����õ� builderAssistant ��
2. cache-ref ��ǩ����
3. cache ��ǩ ��������
```xml
 <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="false" type="PERPETUAL" blocking="false"/>
```
  1. ��ʼ��CacheBuilder. new CacheBuilder(currentNamespace).build()
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



#### 3.SQLִ�й���
1. Ϊ Mapper �ӿ�����ʵ������ִ��SQL  DefaultSqlSession.getMapper(Class<T> type)
    1. MapperProxy ���� UserMapper �ӿ�ִ��
    2. ��������Ƕ����� Object ���еģ���ֱ�ӵ���
    3. ��� isDefaultMethod(method)�� invokeDefaultMethod(proxy, method, args); ��� JDK 1.8 �ӿڵ�Ĭ�Ϸ����ṩ��֧��
    4. �����½� MapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration())�����뻺��
       > ParamNameResolver ����������
    5. ���� execute ����ִ�� SQL mapperMethod.execute(sqlSession, args)

2. ��ѯ���ִ�й���
    1. result = sqlSession.selectOne(command.getName(), param); 
    2. CachingExecutor �������� Map<Cache, TransactionalCache> �������(LRU..)
    3. BaseExecutor һ������ PerpetualCache# Map<Object, Object> cache ���û����ֱ�Ӳ��
    4. ��ѯ���ݿ�queryFromDatabase()#doQuery
    
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
3. �������ִ�й���








   