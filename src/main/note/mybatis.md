
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
3. cache 标签
```xml
 <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="false" type="PERPETUAL" blocking="false"/>
```
4. resultMap 标签
5. sql 标签
6. select|insert|update|delete 标签
7. 建造模式构建 MapperBuilderAssistant# currentCache
`useNewCache(Class<? extends Cache> typeClass, Class<? extends Cache> evictionClass, Long flushInterval, Integer size, boolean readWrite,
      boolean blocking,
      Properties props)`

   