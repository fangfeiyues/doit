
#### 并发问题
> 1. CPU增加了缓存 以均衡与内存的速度差异	(多CPU缓存导致内存可见性问题)
> 2. 操作系统增加了进程线程以分时复用CPU进而均衡CPU与I/O设备的速度差异 (线程切换带来的原子性问题)
> 3. 编译程序优化指令执行次序，似的缓存能够得到更加合理的利用  (单例的double check下 new的过程可能先地址赋值再初始化对象有序性问题??? 如果不是原子性都会存在问题)


#### 多线程 & 单线程
根据实验数据当并发执行累计不超过百万次时，速度会比串行累计慢。主要是因为线程的开销和创建。
> 查看线程上下文：vmstat 10 1  每10s采集一次

procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----  
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st  
 2  0      0 143044 146648 932064    0    0     0     6   12    4  1  0 99  0  0  
 0  0      0 143044 146648 932096    0    0     0     0  227  513  0  0 100  0  0  
 0  0      0 143044 146648 932096    0    0     0     0  198  475  0  0 100  0  0  
 0  0      0 143044 146648 932096    0    0     0     0  202  479  0  0 100  0  0  
 0  0      0 143044 146648 932096    0    0     0     0  210  486  1  1 98  0  0  
> cs 每秒上下文切换次数
 
排查上下文切换步骤
1. jstack命令打印dump线程信息
2. 统计所有线程都是出于什么状态
> grep java.lang.Thread.State dump11 | awk '{print $2$3$4$5}' | sort | uniq -c 

3. 查看线程日志尤其是处于WAITING状态的 尤其是一些JBOSS的工作线程
4. 找到JBOSS的工作线程配置信息，扩大线程数减少上下文的切换


#### 并发线程死锁 & 数据库死锁
1. 互斥  共享资源X和Y只能被一个线程占用  --- 并发下两个线程之间必然存在互斥
2. 占有且等待  线程T1已经取得共享资源X，在等待共享资源Y的时候，不释放共享资源X   --- 一次性申请所有的资源***
3. 不可抢占  其他线程不能强行抢占T1占有的资源								    --- 主动释放
4. 循环等待  线程T1等待线程T2占有的资源，线程T2等待线程T1占有的资源就是循环等待  --- 按序申请资源 
 
#### volatile
`Happens-Before` 的操作对后面是可见的，允许编译器优化但是优化后一定遵守Happens-Before
1. 程序顺序性规则
2. volatile规则，对一个volatile写操作Happens-Before于后续对这个变量的读操作
3. 传递性
4. 管程中锁的规则
5. start规则

volatile变量不仅可以保证自己的变量可见性，还能保证书写在volatile变量写操作之前的操作Happens-Before书写在volatile变量读操作之后的那些操作


#### 内存模型


#### 原子性
一把锁保护多个资源 -- Account.class --> 串行
不要把可变对象当成一把锁 因为在一个操作的时候拿到的值已经被另一个改变了


#### 等待-通知
while(!actr.apply(this, target)) ； --> 等待通知
  wait()/notifyAll()       Sounds good! signall can avoid dead lock better
  CountDownLatch2 -- wait() / countDown()

  
#### 管程Monitor 监视器
并发编程领域两个核心问题：互斥 & 通信。Lock解决互斥问题 Condition解决管程问题
Lock(ReentrantLock) & Synchronized：
	Sync无法破坏死锁的"抢占不可破坏资源"条件,只会一直等待。
	Lock则可中断响应lockInterruptibly()，支持超时tryLock(long time, TimeUnit unit)，支持非阻塞的获取锁tryLock()
    可见性Happens-before, Sync的解锁Happens-before于后续对这个锁的加锁
Semaphore: 允许多个线程访问一个临界区


#### 多线程线程池
为什么是多线程？降低延迟，提高吞吐量 --> 优化算法和发挥硬件性能。后者就是提升I/O利用率和CPU利用率，而多线程主要就是来平衡CPU和I/O
CPU密集 = CPU数
I/O密集：= CPU数*(1+ I/O时间 / CPU时间)

ThreadPoolExecutor(
int corePoolSize,
int maximumPoolSize,
long keepAliveTime,
TimeUnit timeUnit,
BlockingQueue<Runnable> workQueue,
ThreadFactory threadFactory,
RejectedExecutionHandler handler
)
和池化的区别：直接拿取acquire()
	

#### Semaphore信号量

#### 读写锁
1.允许多个线程同时读共享变量
2.只允许一个线程写共享变量


#### 异步化 CompletableFuture
dubbo的异步化改造：CompletableFuture.completedFuture(result);
1.描述串行关系 thenApply,thenAccept,thenRun,
	CompletableFuture<String> f0 = 
	  CompletableFuture.supplyAsync(
		() -> "Hello World")      		//①
	  .thenApply(s -> s + " QQ")        //②
	  .thenApply(String::toUpperCase);  //③
	System.out.println(f0.join());
	// 输出结果 ===> HELLO WORLD QQ
2.描述 AND 汇聚关系
3.描述 OR 汇聚关系
4.异常处理


#### Imm

---
#### CAS
java.util.concurrent包完全建立在CAS上
CAS有三个操作数：内存值V、旧的预期值A、要修改的值B，当且仅当预期值A和内存值V相同时，将内存值修改为B并返回true，否则什么都不做并返回false
CAS也是通过Unsafe(直接操作操作系统的硬件)实现的 由于CAS都是硬件级别的操作，因此效率会高一些





















