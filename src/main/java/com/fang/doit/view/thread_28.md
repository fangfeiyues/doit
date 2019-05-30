#### 1.多线程的几种实现方式，什么是线程安全
1.继承Thread
2.实现Runnable
3.lambda Runnable task = () -> {}
         new Thread(() -> {  }).start();
4.内部类  new Thread() { @Override public void run() { } }.start();

运行的结果和单线程运行结果一致

#### 2.volatile的原理，作用，能代替锁么。
Lock前缀指令会引起处理器缓存回写到内存。
一个处理器的缓存回写到内存会导致其他处理器的缓存无效。嗅探一个处理器来检测其他处理器

为了减轻CPU加载代码压力 对没个线程缓存本地工作空间保存主存的副本
内存屏障  1.阻止屏障两边的指令重排序  2.强制把写缓冲区/高速缓存中的脏数据等写回主内存，让缓存中相应的数据失效
a.lfence，Load Barrier 读屏障
b.sfence, Store Barrier 写屏障
c.mfence, 是一种全能型的屏障具备ifence和sfence的能力
d.Lock前缀，Lock不是一种内存屏障但是它能完成类似内存屏障的功能。Lock会对CPU总线和高速缓存加锁可以理解为CPU指令级的一种锁

指令重排序  load addl $0x0, (%esp)这个命令相当于内存屏障
内存屏障禁止重排就是利用lock把lock前面的“整体”锁住，当前面的完成了之后lock后面“整体”的才能完成，当写完成后，释放锁，把缓存刷新到主内存。
final语义中的内存屏障

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
1.sleep来自Thread类，和wait来自Object类
2.最主要是sleep方法没有释放锁而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法
3.使用范围：wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用 
4.sleep必须捕获不可中断异常


#### 5.sleep和sleep(0)的区别
Sleep(0)，如果线程调度器的可运行队列中有大于或等于当前线程优先级的就绪线程存在，操作系统会将当前线程从处理器上移除，调度其他优先级高的就绪线程运行；如果可运行队列中的没有就绪线程或所有就绪线程的优先级均低于当前线程优先级，那么当前线程会继续执行，就像没有调用 Sleep(0)一样


#### 6.Lock与Synchronized的区别
采用synchronized不需要用户去手动释放锁，当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；
而Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。


#### 7.synchronized的原理是什么，一般用在什么地方(比如加在静态方法和非静态方法的区别，静态方法和非静态方法同时执行的时候会有影响吗)，解释以下名词：重排序，自旋锁，偏向锁，轻量级锁，可重入锁，公平锁，非公平锁，乐观锁，悲观锁。
偏向锁：它会偏向于第一个访问锁的线程。本质上偏向锁就是为了消除CAS，降低Cache一致性流量
轻量级锁：偏向锁运行在一个线程进入同步块的情况下，当第二个线程加入锁争用的时候，偏向锁就会升级为轻量级锁
Synchronized是非公平锁。 Synchronized在线程进入ContentionList时，等待的线程会先尝试自旋获取锁，如果获取不到就进入ContentionList，这明显对于已经进入队列的线程是不公平的，还有一个不公平的事情就是自旋获取锁的线程还可能直接抢占OnDeck线程的锁资源。

   
#### 8.用过哪些原子类，他们的原理是什么。
AtomicInteger为例 
unsafe.getAndSetInt(this, valueOffset, newValue)

#### 9.JUC下研究过哪些并发工具，讲讲原理。


#### 10.用过线程池吗，如果用过，请说明原理，并说说newCache和newFixed有什么区别，构造函数的各个参数的含义是什么，比如coreSize，maxsize等。 
newCache: 0,  Integer.MAX_VALUE; 
newFixed: nThreads, nThreads

+ SynchronousQueue 同步队列 该队列不存储元素，每个插入操作必须等待另一个线程调用移除操作，否则插入操作会一直阻塞

#### 11.线程池的关闭方式有几种，各自的区别是什么。
shutdown 会将线程池的状态设置为SHUTDOWN，同时该方法还会中断空闲线程
shutdownNow 会将线程池状态设置为STOP，并尝试中断所有的线程


#### 12.假如有一个第三方接口，有很多个线程去调用获取数据，现在规定每秒钟最多有10个线程同时调用它，如何做到。
Semphore 计数器
令牌法 以恒定的速度向木桶加企鹅每秒10个

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


#### 23.对 AbstractQueuedSynchronizer 了解多少，讲讲加锁和解锁的流程，独占锁和公平所加锁有什么不同 ???
acquire(int arg) 获取一个资源 if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) 其中tryAcquire()方法由继承类自己实现???
AQS，通过维护一个FIFO 队列来管理多线程的排队工作（独占锁为例）
1.公平竞争的情况下，无法获取同步状态的线程将会被封装成一个节点，置于队列尾部 
  addWaiter(Node)向队尾添加节点 1.先CAS插入  2.如果并发下失败再通过 CAS + 自旋的方式插入节点到队尾 ；transient volatile Node tail
2.入队的线程将会通过自旋的方式获取同步状态，若在有限次的尝试后，仍未获取成功，线程则会被阻塞住
  acquireQueued(Node node, arg) 1.前驱节点是头结点且tryAcquire(arg)则获取同步状态自己为头结点 
                                2.失败后看前驱节点状态如果前驱也在等待则直接中断自旋... parkAndCheckInterrupt()阻塞自己
3.当头结点释放同步状态后，且后继节点对应的线程被阻塞，此时头结点线程将会去唤醒后继节点线程
  unparkSuccessor()唤醒后继节点
4.后继节点线程恢复运行并获取同步状态后，会将旧的头结点从队列中移除，并将自己设为头结点



#### 24.使用synchronized修饰静态方法和非静态方法有什么区别。
修饰静态方法，锁是加在类上的，类的所有对象竞争一把锁。多个对象公用一把锁
修饰非静态方法，锁加在单个对象上，不同对象间没有竞争关系。
如果想要同一个对象


#### 25.简述ConcurrentLinkedQueue和LinkedBlockingQueue的用处和不同之处???
ConcurrentLinkedQueue 并发队列
LinkedBlockingQueue 阻塞安全队列


#### 26.导致线程死锁的原因？怎么解除线程死锁。
1.互斥，共享资源只会被一个线程占用
2.占有且等待，T1已经取得共享资源X在等待Y的时候不释放。-- 只能一次性申请所有资源（用一个单例类控制所有的拿出和释放）
3.不可抢占，其他线程不能抢占 -- 主动释放 ReentrantLock.lockInterruptibly()
4.循环等待                  -- 对资源排序，每次按照顺序取资源那样都是从小到大获取资源就不会死锁循环了
已上四种情况会出现死锁，去除一种即可

    
#### 27.非常多个线程（可能是不同机器），相互之间需要等待协调，才能完成某种工作，问怎么设计这种协调方案 ???
分布式


#### 28.用过读写锁吗，原理是什么，一般在什么场景下用。
ReentrantReadWriteLock，AQS的共享锁模式
场景：读多写少


#### 29.开启多个线程，如果保证顺序执行，有哪几种实现方式，或者如何保证多个线程都执行完 再拿到结果
顺序执行：
多个线程执行完拿到结果：CyclicBarrier 


#### 30.延迟队列的实现方式，delayQueue和时间轮算法的异同
定时器轮询遍历数据库记录： 线程定时扫描
JDK的DelayQueue： 内部封装了PriorityQueue排序先触发的.得手动while循环去getDelayed()是否为零太依赖人为
JDK ScheduledExecutorService
时间轮（netty）HashedWheelTimer:   内部Worker不断工作但是如果存在一个延迟后面就都会因为他是根据自己的起始时间来的
利用quartz等定时任务
Redis的ZSet实现
rocketmq实现延时队列：只有18种延迟等级


thread - jvm - cache - tcp