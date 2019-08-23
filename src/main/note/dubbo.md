<!--Dubbo ѧϰ�ܽ�-->

#### 1.Service ����
|  ����   |    ȱʡ  |  ����    |    ʵ��    |   �ؼ�Դ��    |
|------   | -----------  |  -------  | ---------  |---------  |
| group    |         |  ͬһ���ڵ��ṩ����   | zk����povider_url�� �Ƚ�isMatch provier��consumer�ķ��� empty | ZookeeperRegistry#toUrlsWithEmpty(consumer, path, providers)|
| timeout  |  1000   |  rpc ���ӳ�ʱ����    | 1.get()ͬ��  2.TimeoutCheckTask��ѵ       |     TimeoutCheckTask#DefaultFuture.received(future.getChannel(), timeoutResponse);      |
| connectTimeout  |  5000   |  Netty ��ʼ������    |   Nettyϸ��   |     bootstrap.setOption("connectTimeoutMillis", getConnectTimeout())    |
| connections  |   1    |   �������� client��invokeʱ��ʹ��    |      |  DubboProtocol#getClients()       |
| generic |        |        |         |         |
| url     |        | ��Ե�ֱ�������ṩ�ߵ�ַ�����ƹ�ע������      |         |         |
| stub    |  false |        | StubProxyFactoryWrapper ��proxyFactory��aop wrapper    |         |
| mock    |        |        |         |         |
| token   |string/boolean  | ���Ƶ������Ƿ�ֹ�������ƹ�ע������ֱ�ӷ��� ��֤ע�����ĵ���Ȩ������Ч�����ʹ�õ�Ե���ã���ر����ƹ���  |  PROVIDER��TokenFilter��֤     |         |
| cache   |        |  �Ե��ò���Ϊkey�����淵�ؽ������ѡ��lru, threadlocal, jcache��     |    CacheFilter����<url, new ValueWrapper(result.getValue()>   |         |
| validation  |        |        |         |         |
| actives   | Integer.MAX_VALUE  |  ÿ����������ÿ����ÿ������󲢷�������        |     ActiveLimitFilter    |         |
| executes  | Integer.MAX_VALUE  |  �����ṩ��ÿ����ÿ�������ɲ���ִ��������    |         |         |
| filter    |        | service.filter �����ṩ��Զ�̵��ù������������ƣ���������ö��ŷָ�      |         |         |
| listener  |        | exporter.listener �����ṩ������������������ƣ���������ö��ŷָ�      |         |         |
| layer  |        |  �����ṩ�����ڵķֲ㡣�磺biz��dao��intl:web��china:acton��      |         |         |
| init   |        |        |         |         |


#### 2.���ش��Stub & ����αװMock
1. ���ش��Stub, ������aop��around advice
> 1. ���񷽷������
> 2. ������ڱ��ش��Stub�Ļ�����ִ�б��ش��
> 3. Stub��ִ�е�ʱ�����ִ���Լ����߼�beforeȻ��proxyԶ�̴�������ٷ���after-returning
> 4. ���Proxy���ù����쳣�˻�ִ�����Ѷ˵�Mock���ݴ����ݴﵽ����

`StubProxyFactoryWrapper` �Ǹ�wrapper������� `proxyFactory` ����֮ǰaopִ�С�  
�������Ҫ�߼��� �õ�Զ�̵ĵ��� `DubboInvoker` ��Ϊ��������

2. ����αװMock
�� `MockClusterInvoker` ִ��ʧ���� ���ص��ǿͻ����ṩ�ı���αװ��ʵ�֡�

ͨ��Լ���������õ����������


#### 3.���ص���
> 1. DubboĬ���ڱ�����injvm�ķ�ʽ��¶���������Ļ���ͬһ����������������ĵ��û������߱��ص���
> 2. ���ص���Ĭ�Ͽ��������Ƿ�����ָ��urlֱ��������޷�ʹ��


#### 4.����ͣ��
> ����ͣ��Ĭ�Ͽ���
> 1. ��JVMע�� DubboShutDownHook��ÿ��ShutdownHook����һ���������߳���JVM�˳�ʱ����ִ��
> 2. �ر�����ע�����ġ�ע���Ѿ������ķ���ȡ����ǰ�����ķ��񣻶Ͽ���ע�����ĵ�����
> 3. protocol �� destroy()������Invoker��Exporter; �ر�Server�������������ӵ�client����readonly; �ر�client�Ͽ����ӣ��ͷ���Դ

#### 5.ȫ��־׷��
Dubbo��������Ϣ
 > 1. ���˴��ݡ�
 > 2. �Զ˴��ݡ�`RpcInvocation#setAttachment`����չfilterͨ����ʽ���δ�provider���ݵ�consumer�ﵽȫ��·����ͬһ��traceid

#### 6.�첽�ӿ�ʵ��



#### 7.Dubboʹ��REST
ʹ��
1. �����ڽӿ���


#### 8.Dubbo�������� & RocketMQ
> 1. �ͻ�����ε�֪�����ʧ�ܡ��ͻ��˳�ʱ HashedWheelTimer
> 2. ���������Ҫ�ݴ�ReconnectTimerTask
> 3. ������ⲻ��Ҫæ��⡣�������ݵĽ�ɫӦ����������ɡ������ɡ��ά�� `lastRead & lastWrite` �ֶβ���ÿ����Ϣsent����channel���ֶ�
> 4. ��ʱ����ȷ������1s�Ѿ��Ͽ�����60s�ŷ��֡���Ϊ20


#### 9.HashedWheelTimer


#### 10.Netty#IdleStateHandler


#### 11.Dubboע������


#### 12.��չ�����
�������Class��ʵ��������Ը��ݲ�ͬ���Է�Ϊ��ͬ�����
> 1. ��ͨ��չ�ࡣ
> 2. ��װ��չ�ࡣWrapper�࣬ͨ���߼���������Ҫ�ڹ��췽���д���һ�������ʵ��
> 3. ����Ӧ��չ�ࡣͨ��URLĳЩ������̬ȷ�� @Adaptive

#### 13.Dubbo�߳�ģ��
IOģ��
> ����IO
> ������IO
> IO����

![](image/dubbo_thread.png)





















































