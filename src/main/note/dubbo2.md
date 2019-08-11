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
| actives   | Integer.MAX_VALUE  |  每服务消费者每服务每方法最大并发调用数        |     ActiveLimitFilter    |         |
| executes  | Integer.MAX_VALUE  |  服务提供者每服务每方法最大可并行执行请求数    |         |         |
| filter    |        | service.filter 服务提供方远程调用过程拦截器名称，多个名称用逗号分隔      |         |         |
| listener  |        | exporter.listener 服务提供方导出服务监听器名称，多个名称用逗号分隔      |         |         |
| layer  |        |  服务提供者所在的分层。如：biz、dao、intl:web、china:acton。      |         |         |
| init   |        |        |         |         |


#### 2.Dubbo注册中心


#### 3.扩展点加载
被缓存的Class和实例对象可以根据不同特性分为不同的类别
> 1. 普通扩展类。
> 2. 包装扩展类。Wrapper类，通用逻辑抽象且需要在构造方法中传入一个具体的实现
> 3. 自适应扩展类。通过URL某些参数动态确定 @Adaptive





