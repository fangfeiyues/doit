
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


#### 3.SQLִ�й���












   