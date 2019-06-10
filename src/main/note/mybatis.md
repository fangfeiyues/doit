
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


#### 3.SQL执行过程












   