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


#### 2.Dubboע������


#### 3.��չ�����
�������Class��ʵ��������Ը��ݲ�ͬ���Է�Ϊ��ͬ�����
> 1. ��ͨ��չ�ࡣ
> 2. ��װ��չ�ࡣWrapper�࣬ͨ���߼���������Ҫ�ڹ��췽���д���һ�������ʵ��
> 3. ����Ӧ��չ�ࡣͨ��URLĳЩ������̬ȷ�� @Adaptive





