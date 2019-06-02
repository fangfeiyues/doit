#### 1.Tomcat����ܹ�
> 1. ����Socket���ӣ����������ֽ�����Request��Response�����ת����Connector
> 2. ���غ͹���Servlet���Լ����崦��Request����Container
    
���tomcat�������������� `������Connector` ��
`����Container`������⽻�����ڲ����� 

![tomcat](image/tomcat.png)

1. ***������Connector ��Servlet������Э�鼰I/Oģ�� �������л�õ�ʼ����ServletRequest***
��������Ҫ����������ھ۹��ܣ�1.����I/Oͨ��  2.Ӧ�ò�Э����� ת����Tomcat Request  3.ͳһServletRequest

![Connector](image/connector.png)

    EndPoint ͨ�Ŷ˵� �����Socket�����Acceptor��������SocketProcessor��������
    Processor ��������Ĵ���ʽProtocolHandler��socket�ֽ���������tomcat request/response
    Adapter ʹ��service���� CoyoteAdapter����ģʽ�����request������ServletRequest
    
2. ***����Container ����URL�������ҵ� Servlet Engine -- Host -- Context
   --Servlet***

![Container](image/container.png)

    1.����Э��Ͷ˿ں�ѡ��Service��Engine, 
    2.Ȼ������ѡ��Host,
    3.֮�����URL�ҵ�Context�����ServletContext����Ӧ�õ�webӦ��ApplicationContext������tomcat��StandardContext
    4.������URL�ҵ�Wrapper(Servlet)

����ʹ��������ģʽ����������Valve����һ����������а���next���ԣ�Ȼ��pipeline��Ϊ�������Valve

```java 
// Calling the container
      connector.getService().getContainer().getPipeline().getFirst().invoke(request,
response);
```

Tomcat��host����
 ```
 <server port="8005" shutdown="SHUTDOWN">
    <service name="Catalina">
        <engine defaulthost="localhost" name="Catalina">
            <host appbase="webapps" autodeploy="true" name="localhost" unpackwars="true"></host>
            <host appbase="webapps1" autodeploy="true" name="www.domain1.com" unpackwars="true"></host>
            <host appbase="webapps2" autodeploy="true" name="www.domain2.com" unpackwars="true"></host>
            <host appbase="webapps3" autodeploy="true" name="www.domain3.com" unpackwars="true"></host>
        </engine>
    </service>
</server>
 ```


#### 2.Tomcat ��������

![](image/tomcat���.png)

Tomcat��̬�Ĵ�������������ʼ����ֹͣ��������Ҫ����ͳһ�Ĺ���
���ܶ�̬����ӻ�ɾ��������©���ظ���


#### 3.startup.sh��������
�Ȳ�����ȼ���



```xml
contextInitialized:103, ContextLoaderListener (org.springframework.web.context)
listenerStart:4776, StandardContext (org.apache.catalina.core)
startInternal:5240, StandardContext (org.apache.catalina.core)
start:150, LifecycleBase (org.apache.catalina.util)
addChildInternal:754, ContainerBase (org.apache.catalina.core)
addChild:730, ContainerBase (org.apache.catalina.core)
addChild:734, StandardHost (org.apache.catalina.core)
manageApp:1735, HostConfig (org.apache.catalina.startup)
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)
invoke:300, BaseModelMBean (org.apache.tomcat.util.modeler)
invoke:819, DefaultMBeanServerInterceptor (com.sun.jmx.interceptor)
invoke:801, JmxMBeanServer (com.sun.jmx.mbeanserver)
createStandardContext:482, MBeanFactory (org.apache.catalina.mbeans)
createStandardContext:431, MBeanFactory (org.apache.catalina.mbeans)
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
```
