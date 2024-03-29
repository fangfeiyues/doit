### Java并发工具类

---
#### 1.ConcurrentHashMap 
  > 抛弃了分段锁技术的实现，直接采用 CAS + synchronized 保证并发更新的安全性

---
#### 2.ConcurrentLinkedQueue 
  > 要实现一个线程安全的队列有两种方式：阻塞和非阻塞。阻塞队列无非就是锁的应用，而非阻塞则是CAS算法的应用

offer(E e) 插入
    for (Node<E> t = tail, p = t;;)  volatile Node<E> tail 开始自旋 
      q = p.next; 
   1. if q == null tail的下一个为空情况 
   2. if (p.casNext(null, newNode)) 
   3. if (p != t) 
   4. casTail(t, newNode)  更新尾节点时如果刚好又插入了一个
   5. if (p == q) 等于自身即尾节点可能被删
   6. p = (t != (t = tail)) ? t : head;
   7. else  p = (p != t && t != (t = tail)) ? t : q;   p已经不是尾节点 

---
#### 3.LinkedBlockingQueue
final ReentrantLock putLock = new ReentrantLock()
final Condition notFull = putLock.newCondition()
  > 基于链表方式实现 入队
  >  1. void put(E e)  
       队列未满：入队并判断下一个能否放入可以则唤醒notFull.signal()； 
       队列已满 notFull.await() 阻塞处理
  >  2. boolean offer(E e)  
       队列已满 notFull.await() 直接返回false
  >  3. boolean offer(E e, long timeout, TimeUnit unit)
       队列已满 等待nanos = notFull.awaitNanos(nanos)
       
       
加可重入锁
takeLock.lockInterruptibly();
  > 出队 先入先出
  > 1. E take():  notEmpty.await() & dequeue()  if (c > 1){ notEmpty.signal(); }
  > 2. E poll()
  > 3. E poll(long timeout, TimeUnit unit);
  
dequeue()出队的实现 ???
takeLock.unlock();
获取元素 peek()  加锁拿到但不移除

#### 4.ArrayBlockingQueue
数组  + 一个 ReentrantLock 锁


#### 5.DelayQueue
#### 6.PriorityClockingQueue

---
#### 7.CopyOnWriteArrayList
#### 8.CopyOnWriteArraySet


--- 
#### CAS
Lock前缀保证在多处理器环境下，LOCK# 信号可以确保处理器独占使用某些共享内存
第一种方式是通过锁定总线 可能会导致其他核心短时内停止工作
第二种方式是锁定缓存

ABA
1.版本 
2.AtomicStampedReference

---
#### 9.AbstractQueuedSynchronizer 队列同步器
> 1. volatile int state:  >0 表示获取锁； =0表示释放锁
> 2. Node # CANCELLED = 1 因为超时或者中断，节点会被设置为取消状态，被取消的节点时不会参与到竞争中的
> 3. Node # SIGNAL = -1 后继节点的线程处于等待状态，而当前节点的线程如果释放了同步状态或者被取消，将会唤醒通知后继节点，使后继节点的线程得以运行
> 4. Node # CONDITION = -2 节点在等待队列中，节点线程等待在Condition上，当其他线程对Condition调用了signal()后，该节点将会从等待队列中转移到同步队列中，加入到同步状态的获取中
> 5. Node # PROPAGATE = -3  表示下一次共享式同步状态获取，将会无条件地传播下去
> 6. Node # waitStatus  等待状态
> 7. Node # nextWaiter  等待队列中的后续节点。如果当前节点是共享的，那么字段将是一个 SHARED 常量，也就是说节点类型（独占和共享）和等待队列中的后续节点共用同一个字段  

1. 获取同步状态 acquire(args)
      |
      |
!tryAcquire(args): 获取同步状态 以ReentrantLock为例，
      1.获取到当前的state==0 则表示无锁状态可以直接获取  
      2. 否则 公平 - 判断该线程是否位于 CLH 队列的列头，从而实现公平锁 
      3. 没有CAS插入 return true
      4.重入判断setState(c + acquires)
      |
      |
addWriter(Node.EXCLUSIVE)：在没有获取到同步状态下 加入到等待队列
      1.CAS插入
      2.自旋 + CAS插入
      |
      |
acquireQueued(Node , arg)：再次获取同步状态
      1.pre节点为头结点 且 tryAcquire(args)成功 则直接设为头结点
      2.否则 shouldParkAfterFailedAcquire(p, node)   如果获取同步状态失败，则根据条件判断是否应该阻塞自己  如果不阻塞，CPU 就会处于忙等状态，这样会浪费 CPU 资源
          waitState == SIGNAL(-1) 等待唤醒 return true 中断；当线程在获取同步状态失败时，根据前驱节点的等待状态，决定后续的动作。 
                                  比如前驱节点等待状态为 SIGNAL，表明当前节点线程应该被阻塞住了。不能老是尝试，避免 CPU 忙等
          waitState == CANCELLED(1) 不参与竞争 不断回查； return false
          waitState == 0 / PROPAGATE(-3) 设置 pred = SIGNAL 等待唤醒 return false
        即只有在自己的上个节点等待状态为SIGNAL 才会中断 否则不断自旋轮询 ???
      3.parkAndCheckInterrupt()  LockSupport.park(this) 阻塞自己

2. 中断异常
 doAcquireInterruptibly(int arg) throws InterruptedException { 
       shouldParkAfterFailedAcquire(p, node)  -- pre.waitState == SIGNAL为什么要上个节点状态为等待通知 才能中断 ???
       && parkAndCheckInterrupt()   -- Thread.interrupted()
         --> throw new InterruptedException()
       }

3. 超时控制
tryAcquireNanos(int arg, long nanos) 它除了响应中断外，还有超时控制


4. 独占锁与共享锁的：公平下先判断头结点是否自己 是就直接获取否拿取 state>0（！！！） 自旋等待
Semaphore#
acquireShared(int arg)
    |
if (hasQueuedPredecessors())     -1公平模式下 如果当前线程非头结点则获取失败
    |
remaining = available - acquires
remaining < 0 ||
compareAndSetState(available, remaining)  小于0 或者大于0就更新 返回
    |
doAcquireSharedInterruptibly    小于0 此时将请求线程放入 AQS 的同步队列中进行等待
   head == null 尝试获取
   判断pred.waitState == SIGNAL 且挂起后是否还中断  是则中断异常否则继续自旋

其主要逻辑是：
1. 调用自定义同步器实现的tryAcquire(int arg)方法该方法保证线程安全的获取同步方
2. 如果同步状态获取失败则构造同步节点（独占式Node.EXCLUSIVE）并通过addWrite(Node)方法将该节点接入到同步队列的尾部
3. 最后acquireQueued(Node)使得该节点以死循环方式获取同步状态如果获取不到则阻塞节点（只能靠前节点唤醒或阻塞节点被中断）


---
#### 10.ReentrantLock 
> synchronized 的比较

https://blog.csdn.net/xueba8/article/details/88753443
1. 锁抢占
> 对象的class锁，是 monitorenter 和 monitorexit进行重入。 JVM通过字节码显式的去获取和释放monitor实现同步
> 方法锁，是flags: ACC_SYNCHRONIZED。是检查方法的ACC_SYNCHRONIZED标志是否被设置，如果设置了则线程需要先去获取monitor， 执行完毕了线程再释放monitor
> synchronized的语义底层是通过一个monitor的对象完成，其实wait、notiyf和notifyAll等方法也是依赖于monitor对象来完成的，
> 这也就是为什么需要在同步方法或者同步代码块中调用的原因(需要先获取对象的锁，才能执行)，
> 否则会抛出java.lang.IllegalMonitorStateException的异常

2. 锁获取 -- Java对象头/Monitor对象
> 那锁的状态为其它状态的时候是不是就没用上monitor对象？答案:是的。
> 这也是JVM对synchronized的优化，我们知道重量级锁的monitor实现是基于底层操作系统的mutex互斥原语的，这个开销是很大
> 所以JVM对synchronized做了优化，JVM先利用对象头实现锁的功能，如果线程的竞争过大则会将锁升级(膨胀)为重量级锁，也就是使用monitor对象。
> 当然JVM对锁的优化不仅仅只有这个，还有引入适应性自旋、锁消除、锁粗化、轻量级锁、偏向锁等。

3. 轻量级锁
Mark Word: 记录了对象和锁的相关信息，存储对象自身的运行时数据
1. 哈希吗
2. GC分代年龄
3. 锁状态标志
4. 线程持有锁
5. 偏向线程ID
6. 偏向时间戳

![](image/synchronized.png)

#### 11.ReentrantReadWriteLock



---
#### 12.CountdownLatch
1. new CountDownLatch(count) -- AQS.state = count
2. countDown() -- AQS.releaseShared(1)
   tryReleaseShared(1) 尝试释放
     c = getState()
        c为0即所有的state都已经释放
        否则自旋 + CAS 设置新的state = state -1
3. await()
   AQS.acquireSharedInterruptibly()
     tryAcquireShared(arg)
      (getState() == 0) ? 1 : -1    即判断当前是否为0
         doAcquireSharedInterruptibly(1)
           1.Node = addWaiter(Node.SHARED)   将当前线程包装成一个 Node 对象，加入到 AQS 的队列尾部
           2.Node = node.predecessor()       如果他前面的 node 是 head ，便可以尝试获取锁了
           3.如果不是，则阻塞等待，调用的是 LockSupport.park(this);
  AQS.tryAcquireSharedNanos(arg,nanos)    中断 + 超时

#### 13.Cyclicbarrier

---
#### 14.Semaphore 信号量
如上


---
#### 15.atmoicInteger


---
#### 16.ThreadPoolExecutor
ThreadLocal








