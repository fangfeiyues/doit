
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
3. cache ��ǩ
```xml
 <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="false" type="PERPETUAL" blocking="false"/>
```
4. resultMap ��ǩ
5. sql ��ǩ
6. select|insert|update|delete ��ǩ
7. ����ģʽ���� MapperBuilderAssistant# currentCache
`useNewCache(Class<? extends Cache> typeClass, Class<? extends Cache> evictionClass, Long flushInterval, Integer size, boolean readWrite,
      boolean blocking,
      Properties props)`

   