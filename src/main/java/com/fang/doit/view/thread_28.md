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

内存屏障：是一组处理器指令，用于实现对内存操作的顺序限制


#### 3.画一个线程的生命周期状态图



#### 4.sleep和wait的区别
1.sleep来自Thread类，和wait来自Object类
2.最主要是sleep方法没有释放锁而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法
3.使用范围：wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用 
4.sleep必须捕获不可中断异常


#### 5.sleep和sleep(0)的区别



#### 6.Lock与Synchronized的区别
采用synchronized不需要用户去手动释放锁，当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；
而Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。


#### 7.synchronized的原理是什么，一般用在什么地方(比如加在静态方法和非静态方法的区别，静态方法和非静态方法同时执行的时候会有影响吗)，
     解释以下名词：重排序，自旋锁，偏向锁，轻量级锁，可重入锁，公平锁，非公平锁，乐观锁，悲观锁。
   
   
#### 8.用过哪些原子类，他们的原理是什么。



#### 9.JUC下研究过哪些并发工具，讲讲原理。



#### 10.用过线程池吗，如果用过，请说明原理，并说说newCache和newFixed有什么区别，构造函数的各个参数的含义是什么，比如coreSize，maxsize等。


#### 11.线程池的关闭方式有几种，各自的区别是什么。


#### 12.假如有一个第三方接口，有很多个线程去调用获取数据，现在规定每秒钟最多有10个线程同时调用它，如何做到。
漏斗法，以每秒一定的速度消费漏斗内10个数据

#### 13.spring的controller是单例还是多例，怎么保证并发的安全。
单例
？

#### 14.用三个线程按顺序循环打印abc三个字母，比如abcabcabc


#### 15.ThreadLocal用过么，用途是什么，原理是什么，用的时候要注意什么。
线程安全下存储数据


#### 16.如果让你实现一个并发安全的链表，你会怎么做。
ConcurrentLinkedQueue?

#### 17.有哪些无锁数据结构他们实现的原理是什么。
AtmoInteger 
CAS

#### 18.讲讲java同步机制的wait和notify。


#### 19.CAS机制是什么，如何解决ABA问题。


#### 20.多线程如果线程挂住了怎么办。


#### 21.countdowlatch和cyclicbarrier的内部原理和用法，以及相互之间的差别(比如countdownlatch的await方法和是怎么实现的)。


#### 23.对AbstractQueuedSynchronizer了解多少，讲讲加锁和解锁的流程，独占锁和公平所 加锁有什么不同。


#### 24.使用synchronized修饰静态方法和非静态方法有什么区别。


#### 25.简述ConcurrentLinkedQueue和LinkedBlockingQueue的用处和不同之处。


#### 26.导致线程死锁的原因？怎么解除线程死锁。


#### 27.非常多个线程（可能是不同机器），相互之间需要等待协调，才能完成某种工作，问怎么设计这种协调方案。


#### 28.用过读写锁吗，原理是什么，一般在什么场景下用。


#### 29.开启多个线程，如果保证顺序执行，有哪几种实现方式，或者如何保证多个线程都执行完 再拿到结果

CyclicBarrier 


#### 30.延迟队列的实现方式，delayQueue和时间轮算法的异同
定时器轮询遍历数据库记录： 线程定时扫描
JDK的DelayQueue： 内部封装了PriorityQueue排序先触发的.得手动while循环去getDelayed()是否为零太依赖人为
JDK ScheduledExecutorService
时间轮（netty）HashedWheelTimer:   内部Worker不断工作但是如果存在一个延迟后面就都会因为他是根据自己的起始时间来的
利用quartz等定时任务
Redis的ZSet实现
rocketmq实现延时队列：只有18种延迟等级


thread - jvm - cache - tcp