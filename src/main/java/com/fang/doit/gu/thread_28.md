#### 1.多线程的几种实现方式，什么是线程安全
1.继承Thread
2.实现Runnable
3.lambda Runnable task = () -> {}
         new Thread(() -> {  }).start();
4.内部类  new Thread() { @Override public void run() { } }.start();

运行的结果和单线程运行结果一致

#### 2.volatile的原理，作用，能代替锁么。
> 1. 关键字volatile可以用来修饰成员变量就是告知程序任何对该变量的访问均需要从共享内存获取，而对它的改变必须同步刷新回共享内存
> 2. volatile转成汇编代码时候会有 Lock指令
> 2.1 将当前处理器缓存行的数据写回到系统内存
> 2.2 同时会使在其他CPU里缓存了该内存地址的数据无效



#### 3.画一个线程的生命周期状态图
初始状态  
   |
   |
可运行状态/运行状态  -----------------------------   休眠状态包括blocked,waiting,timed_waiting
   |        (Synchronized/ wait(),join(),LockSupport()/ +sleep() )
   |
终止状态

注1.阻塞式API，线程阻塞指的是操作系统线程状态Java依然保持RUNNABLE状态
注2.stop()立即终止 interrupt()会通过异常通知或主动监测
   interrupt 此方法是实例方法，用于告诉此线程外部有中断请求，并且将线程中的中断标记设置为true，而不是立即中断
   interrupted 此方法是类方法，用来判断当前线程是否已经中断。线程的中断状态由该方法清除
   isInterrupted 此方法是实例方法，用来判断线程是否已经中断。线程的中断状态不受该方法的影响。
   
#### 4.sleep和wait的区别
> 1. sleep来自Thread类，和wait来自Object类
> 2. 最主要是sleep方法没有释放锁而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法
> 3. 使用范围：wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用 
> 4. sleep必须捕获不可中断异常


#### 5.sleep和sleep(0)的区别
Sleep(0)，如果线程调度器的可运行队列中有大于或等于当前线程优先级的就绪线程存在，操作系统会将当前线程从处理器上移除，调度其他优先级高的就绪线程运行；
如果可运行队列中的没有就绪线程或所有就绪线程的优先级均低于当前线程优先级，那么当前线程会继续执行，就像没有调用 Sleep(0)一样


#### 6.Lock与Synchronized的区别
> 1. Lock尝试非阻塞的获取锁. tryLock()
> 2. Synchronized不需要用户去手动释放锁；而Lock则必须要用户去手动释放锁如果没有主动释放锁就有可能导致出现死锁现象
> 3. Lock 能被中断获取锁。lockInterruptibly()
> 4. Lock 超时获取锁. tryLock(long time, TimeUnit unit)


#### 7.synchronized的原理是什么，一般用在什么地方(比如加在静态方法和非静态方法的区别，静态方法和非静态方法同时执行的时候会有影响吗)，解释以下名词：重排序，自旋锁，偏向锁，轻量级锁，可重入锁，公平锁，非公平锁，乐观锁，悲观锁。
> 普通同步方法锁当前实例；静态同步方法锁是当前类的Class对象
> monitorenter, monitorexit 

> 1. 偏向锁：它会偏向于第一个访问锁的线程。本质上偏向锁就是为了消除CAS，降低Cache一致性流量
> 2. 轻量级锁：偏向锁运行在一个线程进入同步块的情况下，当第二个线程加入锁争用的时候，偏向锁就会升级为轻量级锁
> 3. Synchronized是非公平锁。?Synchronized在线程进入ContentionList时，等待的线程会先尝试自旋获取锁，如果获取不到就进入ContentionList，这明显对于已经进入队列的线程是不公平的，还有一个不公平的事情就是自旋获取锁的线程还可能直接抢占OnDeck线程的锁资源。
   
#### 8.用过哪些原子类，他们的原理是什么。


#### 9.JUC下研究过哪些并发工具，讲讲原理。


#### 10.用过线程池吗，如果用过，请说明原理，并说说newCache和newFixed有什么区别，构造函数的各个参数的含义是什么，比如coreSize，maxsize等。 
> 1. newCache: 0,  Integer.MAX_VALUE; 
> 2. newFixed: nThreads, nThreads

+ SynchronousQueue 同步队列 该队列不存储元素，每个插入操作必须等待另一个线程调用移除操作，否则插入操作会一直阻塞

#### 11.线程池的关闭方式有几种，各自的区别是什么。
> 1. shutdown 会将线程池的状态设置为SHUTDOWN，同时该方法还会中断空闲线程
> 2. shutdownNow 会将线程池状态设置为STOP，并尝试中断所有的线程
> 3. interrupt 用于告诉此线程外部有中断请求, 将线程中的中断标记设置为true，而不是立即中断
> 4. interrupted 线程的中断状态由该方法清除
> 5. isInterrupted 线程的中断状态不受该方法的影响

#### 12.假如有一个第三方接口，有很多个线程去调用获取数据，现在规定每秒钟最多有10个线程同时调用它，如何做到。
> Semphore 计数器
> 令牌法 以恒定的速度向木桶加每秒10个

#### 13.spring的controller是单例还是多例，怎么保证并发的安全???


#### 14.用三个线程按顺序循环打印abc三个字母，比如abcabcabc


#### 15.ThreadLocal用过么，用途是什么，原理是什么，用的时候要注意什么 ???
线程安全下存储数据
注意用完之后remove否则会内存溢出


#### 16.如果让你实现一个并发安全的链表，你会怎么做 ???
ConcurrentLinkedQueue


#### 17.有哪些无锁数据结构他们实现的原理是什么。
AtmoInteger 
CAS


#### 18.讲讲java同步机制的wait和notify。
notify()和notifyAll()


#### 19.CAS机制是什么，如何解决ABA问题。
CompareAndSwitch


#### 20.多线程如果线程挂住了怎么办。


#### 21.countdowlatch和cyclicbarrier的内部原理和用法，以及相互之间的差别(比如countdownlatch的await方法和是怎么实现的) ???
和java的wait()及notify()


#### 23.对 AbstractQueuedSynchronizer 了解多少，讲讲加锁和解锁的流程，独占锁和公平所加锁有什么不同
> 队列同步器面向的是锁的实现者，它简化了锁的实现方式屏蔽了同步状态管理，线程排队，等待与唤醒等底层操作


**加锁和解锁的流程**
> 1. tryAcquire() 获取同步锁。由子类根据场景实现如ReentrantLock的fair及noFair
> 2. 获取失败，加入队列尾部
> 3. 如果前驱节点为头节点，获取同步状态

> 1. (共享锁这里的getState()即获取还剩的Node)


#### 24.使用synchronized修饰静态方法和非静态方法有什么区别。
修饰静态方法，锁是加在类上的，类的所有对象竞争一把锁。多个对象公用一把锁
修饰非静态方法，锁加在单个对象上，不同对象间没有竞争关系。
如果想要同一个对象


#### 25.简述ConcurrentLinkedQueue和LinkedBlockingQueue的用处和不同之处???
ConcurrentLinkedQueue 并发队列
LinkedBlockingQueue 阻塞安全队列


#### 26.导致线程死锁的原因？怎么解除线程死锁。
> 1. 互斥，共享资源只会被一个线程占用
> 2. 占有且等待，T1已经取得共享资源X在等待Y的时候不释放。-- 只能一次性申请所有资源（用一个单例类控制所有的拿出和释放）
> 3. 不可抢占，其他线程不能抢占 -- 主动释放 ReentrantLock.lockInterruptibly()
> 4. 循环等待                  -- 对资源排序，每次按照顺序取资源那样都是从小到大获取资源就不会死锁循环了
> 已上四种情况会出现死锁，去除一种即可

    
#### 27.非常多个线程（可能是不同机器），相互之间需要等待协调，才能完成某种工作，问怎么设计这种协调方案 ???



#### 28.用过读写锁吗，原理是什么，一般在什么场景下用。
ReentrantReadWriteLock，AQS的共享锁模式
场景：读多写少


#### 29.开启多个线程，如果保证顺序执行，有哪几种实现方式，或者如何保证多个线程都执行完 再拿到结果



#### 30.延迟队列的实现方式，delayQueue和时间轮算法的异同
定时器轮询遍历数据库记录： 线程定时扫描
JDK的DelayQueue： 内部封装了PriorityQueue排序先触发的.得手动while循环去 getDelayed()是否为零太依赖人为
JDK ScheduledExecutorService
时间轮（netty）HashedWheelTimer:   内部Worker不断工作但是如果存在一个延迟后面就都会因为他是根据自己的起始时间来的
利用quartz等定时任务
Redis的ZSet实现
rocketmq实现延时队列：只有18种延迟等级


thread - jvm - cache - tcp

#### 31.线程上下文切换
从 RUNNABLE 转为非 RUNNABLE 的过程就是线程的上下文切换。
它诱发的有两种 一是程序本身触发的切换 一种是系统或虚拟机诱发的
频繁的线程上下文切换对性能有很大的影响，包括寄存器的存储内容；程序计数器存储的指令内容。
这也是Redis, NodeJS选择单线程串行的优势
> vmstat 2
> vmstat -w -l -p 998 1 100

优化
> 1. 较少锁的持有时间。多线程对锁的竞争会引起上下文的竞争
> 2. 降低锁的粒度。读写锁，锁分段如ConcurrentHashMap
> 3. 非阻塞乐观锁替代竞争锁。volatile, CAS , Synchronized
> 4. wait/notify优化。使用Object.notify()唤醒指定线程方式
> 5. 合理设置线程池。一旦线程池的工作线程数超过系统所拥有的处理器数量就会导致过多的上下文切换 Executors.newCachedThreadPool()就很危险
> 6. 使用协程实现非阻塞等待。
> 7. Java虚拟机垃圾回收。





































