#### 1.用java自己实现一个LRU -- 2021.04.10
> 1.Map+双向链表。解决查询和节点移动问题
> 2.带着时间戳的TreeMap，进行自动排序。

#### 2.分布式集群下如何做到唯一序列号


#### 3.设计一个秒杀系统，30分钟没付款就自动关闭交易 -- 2021.04.10
 

#### 4.如何使用redis和zookeeper实现分布式锁？有什么区别优缺点，会有什么问题，分别适用什么场景。（延伸：如果知道redlock，讲讲他的算法实现，争议在哪里）


#### 5.如果有人恶意创建非法连接，怎么解决


#### 6.分布式事务的原理，优缺点，如何使用分布式事务，2pc 3pc 的区别，解决了哪些问题，还有 -- 2021.04.10
> 2pc: 存在一个预处理请求。1.流程较长易阻塞 2. 一旦资源服务器挂一直等待  3.可能不一致情况
> 3pc：把准备阶段分为了 准备+预处理 +超时，第一阶段只是询问是否可以执行
> TCC：事务补偿机制 很像编排式的问题
> Seata开源框架


#### 7.哪些问题没解决，如何解决，你自己项目里涉及到分布式事务是怎么处理的
> 1. 协同式
> 2. 编排式
> 3. Saga

#### 8.什么是一致性hash -- 2021.04.10
> 虚拟节点


#### 9.什么是restful，讲讲你理解的restful


#### 10.如何设计一个良好的API


#### 11.如何设计建立和保持100w的长连接


#### 12.解释什么是MESI协议(缓存一致性)


#### 13.说说你知道的几种HASH算法，简单的也可以 -- 2021.04.10
> 1.加法HASH. 即将元素的值相加起来做整除
> 2.位运算HASH. 各种位运算（常见的是移位和异或）来充分的混合输入元素
> 3.乘法HASH. hash = 33*hash + key.charAt(i)

#### 14.什么是paxos算法， 什么是zab协议 -- 2021.04.10


#### 15.一个在线文档系统，文档可以被编辑，如何防止多人同时对同 一份文档进行编辑更新


#### 16.线上系统突然变得异常缓慢，你如何查找问题 --2021.04.10


#### 16.1 进程忽然消失了 -- 2021.04.10
> 1. linux的OOM killer
> 2. JVM自身故障 hs_err_pid.log的文件记录了崩溃时jvm的信息以及栈信息等巨复杂文件
> 3. JVM的OOM 找到dump的快照文件

-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=*/java.hprof；

#### 16.2 如何排查cpu100%，机器内存出问题，怎么排查 -- 2021.04.11
> 

#### 17.说说你平时用到的设计模式


#### 18.Dubbo的原理，有看过源码么，数据怎么流转的，怎么实现集群，负载均衡，服务注册 和发现，重试转发，快速失败的策略是怎样的
> 1. 集群:
> 2. 负载均衡：
> 3. 重试转发：
> 4. 快速失败策略：


#### 19.一次RPC请求的流程是什么


#### 20.自己实现过rpc么，原理可以简单讲讲。Rpc要解决什么问题


#### 21.异步模式的用途和意义


#### 22.编程中自己都怎么考虑一些设计原则的，比如开闭原则，以及在工作中的应用 --2021.04.11见《架构整洁之道》
> 职责单一原则. 任何一个软件模块都应该
> 开闭原则
> 里氏替换原则
> 接口隔离原则
> 依赖反转原则


#### 23.设计一个社交网站中的“私信”功能，要求高并发、可扩展等等。 画一下架构图


#### 24.MVC模式，即常见的MVC框架


#### 25.聊下曾经参与设计的服务器架构并画图，谈谈遇到的问题，怎么解决的


#### 26.应用服务器怎么监控性能，各种方式的区别


#### 27.如何设计一套高并发支付方案，架构如何设 -- 2021.04.11


#### 28.如何实现负载均衡，有哪些算法可以实现

#### 29.Zookeeper的用途，选举的原理是什么


#### 30.Zookeeper watch机制原理


#### 31.Mybatis的底层实现原理


#### 32.思考一个方案，实现分布式环境下的countDownLatch -- 2021.04.11


#### 33.后台系统怎么防止请求重复提交


#### 34.描述一个服务从发布到被消费的详细过程


#### 35.!讲讲你理解的服务治理


#### 36.如何做到接口的幂等性


#### 37.如何做限流策略，令牌桶和漏斗算法的使用场景


#### 38.什么叫数据一致性，你怎么理解数据一致性


#### 39.分布式服务调用方，不依赖服务提供方的话，怎么处理服务方挂掉后，大量无效资源请求的浪费，如果只是服务提供方吞吐不高的时候该怎么做，如果服务挂了，那么一会重启，该怎 么做到最小的资源浪费，流量半开的实现机制是什么
>


#### 40.dubbo的泛化调用怎么实现的，如果是你，你会怎么做
> 


#### 41.远程调用会有超时现象，如果做到优雅的控制，JDK自带的超时机制有哪些，怎么实现的 -- 2021.04.11
> 1. Dubbo：时间轮算法环形结构可以根据超时时间的 hash 值(这个 hash 值实际上就是ticks & mask)将 task 分布到不同的槽位中
> 当 tick 到那个槽位时只需要遍历那个槽位的 task 即可知道哪些任务会超时(而使用线性结构, 你每次 tick 都需要遍历所有 task)
> 所以我们任务量大的时候, 相应的增加 wheel 的 ticksPerWheel 值可以减少 tick 时遍历任务的个数.
> 2. 

#### 42.分布式限流如何实现，原理是什么 -- 2021.04.11
> 1.最简单的单机版Semphore
> 2.滑动窗口；不能完全解决精度问题；当突发流量一来马上死机
> 3.漏桶. 流量向恒速流水的的漏桶内加入一旦溢出则不可再请求
> 4.令牌桶. 到装满令牌并恒速度加入令牌的桶子获取令牌一旦令牌取万则停止。 Guava RateLimiter可预先获取下次的
> 