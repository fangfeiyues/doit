
#### 并发问题
> CPU增加了缓存 以均衡与内存的速度差异	(多CPU缓存导致内存可见性问题)
> 操作系统增加了进程线程以分时复用CPU进而均衡CPU与I/O设备的速度差异 (线程切换带来的原子性问题)
> 编译程序优化指令执行次序，似的缓存能够得到更加合理的利用  (单例的double check下 new的过程可能先地址赋值再初始化对象有序性问题??? 如果不是原子性都会存在问题)

#### 可见性 & 有序性
`Happens-Before` 的操作对后面是可见的，允许编译器优化但是优化后一定遵守Happens-Before
1. 程序顺序性规则
2. volatile规则，对一个volatile写操作Happens-Before于后续对这个变量的读操作
3. 传递性
4. 管程中锁的规则
5. start规则

> volatile变量不仅可以保证自己的变量可见性，还能保证书写在volatile变量写操作之前的操作Happens-Before书写在volatile变量读操作之后的那些操作
> 可以这样理解volatile变量的写入和读取操作流程：
> 1. 首先，volatile变量的操作会禁止与其它普通变量的操作进行重排序，例如上面代码中会禁止initialized = true与它上面的两行代码进行重排序(但是它上面的代码之间是可以重排序的)，否则会导致程序结果错误。volatile变量的写操作就像是一条基准线，到达这条线之后，不管之前的代码有没有重排序，反正到达这条线之后，前面的操作都已完成并生成好结果。
> 2. 然后，在volatile变量写操作发生后，A线程会把volatile变量本身和书写在它之前的那些操作的执行结果一起同步到主内存中。
> 3. 最后，当B线程读取volatile变量时，B线程会使自己的CPU缓存失效，重新从主内存读取所需变量的值，这样无论是volatile本身，还是书写在volatile变量写操作之前的那些操作结果，都能让B线程感知到，也就是上面程序中的initialized和configOptions变量的最新值都可以让线程B感知到
volatile写入屏障 happens-before

#### 原子性
一把锁保护多个资源 -- Account.class --> 串行
不要把可变对象当成一把锁 因为在一个操作的时候拿到的值已经被另一个改变了

#### 死锁
a.互斥  共享资源X和Y只能被一个线程占用  --- 并发下两个线程之间必然存在互斥
b.占有且等待  线程T1已经取得共享资源X，在等待共享资源Y的时候，不释放共享资源X   --- 一次性申请所有的资源***
c.不可抢占  其他线程不能强行抢占T1占有的资源								    --- 主动释放
d.循环等待  线程T1等待线程T2占有的资源，线程T2等待线程T1占有的资源就是循环等待  --- 按序申请资源

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





















