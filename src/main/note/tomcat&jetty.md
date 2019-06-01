#### 1.Tomcat����ܹ�
  > 1.����Socket���ӣ����������ֽ�����Request��Response�����ת����Connector
    2.���غ͹���Servlet���Լ����崦��Request����Container
    
���tomcat�������������� `������Connector` ��
`����Container`������⽻�����ڲ����� 

![tomcat](image/tomcat.png)

1. ������Connector
   ��Servlet������Э�鼰I/Oģ���������л�õ�ʼ����ServletRequest

![Connector](image/connector.png)

    EndPoint ͨ�Ŷ˵� �����Socket�����Acceptor��������SocketProcessor��������
    Processor ��������Ĵ���ʽProtocolHandler��socket�ֽ���������tomcat request/response
    Adapter ʹ��service���� CoyoteAdapter����ģʽ�����request������ServletRequest
    
2. ����Container ����URL�������ҵ�Servlet Engine -- Host -- Context
   --Servlet

![111](image/container.png)

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
 ```xml 
 <server port=��8005�� shutdown=��SHUTDOWN��>
    <service name=��Catalina��>
        <engine defaulthost=��localhost�� name=��Catalina��>
            <host appbase=��webapps�� autodeploy=��true�� name=��localhost�� unpackwars=��true��></host>
            <host appbase=��webapps1�� autodeploy=��true�� name=��www.domain1.com�� unpackwars=��true��></host>
            <host appbase=��webapps2�� autodeploy=��true�� name=��www.domain2.com�� unpackwars=��true��></host>
            <host appbase=��webapps3�� autodeploy=��true�� name=��www.domain3.com�� unpackwars=��true��></host>
        </engine>
    </service>
</server>
 ```



#### tomcat����ػ���