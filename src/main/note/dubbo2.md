<!--Dubbo 学习总结-->

#### 1.Service 参数
|  属性   |    缺省  |  作用    |    实现    |   关键源码    |
|------   | -----------  |  -------  | ---------  |---------  |
| group    |         |  同一组内的提供服务   | zk创建povider_url后 比较isMatch provier和consumer的符号 empty | ZookeeperRegistry#toUrlsWithEmpty(consumer, path, providers)|
| timeout  |  1000   |  rpc 连接超时控制    | 1.get()同步  2.TimeoutCheckTask轮训       |     TimeoutCheckTask#DefaultFuture.received(future.getChannel(), timeoutResponse);      |
| connectTimeout  |  5000   |  Netty 初始化连接    |   Netty细节   |     bootstrap.setOption("connectTimeoutMillis", getConnectTimeout())    |
| connections  |   1    |   创建几个 client做invoke时候使用    |      |  DubboProtocol#getClients()       |
| generic |        |        |         |         |
| url     |        | 点对点直连服务提供者地址，将绕过注册中心      |         |         |
| stub    |  false |        | StubProxyFactoryWrapper 是proxyFactory的aop wrapper    |         |
| mock    |        |        |         |         |
| token   |string/boolean  | 令牌的作用是防止消费者绕过注册中心直接访问 保证注册中心的授权功能有效，如果使用点对点调用，需关闭令牌功能  |  PROVIDER下TokenFilter验证     |         |
| cache   |        |  以调用参数为key，缓存返回结果，可选：lru, threadlocal, jcache等     |    CacheFilter缓存<url, new ValueWrapper(result.getValue()>   |         |
| validation  |        |        |         |         |
| actives   |        |        |         |         |
| filter    |        |        |         |         |
| listener  |        |        |         |         |
| layer  |        |        |         |         |
| init   |        |        |         |         |
| executes   |        |        |         |         |




