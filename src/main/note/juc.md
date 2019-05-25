### Java并发工具类

##### 1.ConcurrentHashMap 
  > 抛弃了分段锁技术的实现，直接采用CAS + synchronized保证并发更新的安全性


     



------------- HashTable ---------


------------- 4.TreeMap ----------


------------- 5-6.ConcurrentLinkedQueue & LinkedBlockingQueue ----------
mq怎么保证队列安全的？
DelayQueue


------------- 7.AbstractQueuedSynchronizer  --------
:ReentrantLock, ReentrantReadWriteLock, ThreadPoolExecutor,CountDownLatch
共享锁：
acquireShared(int arg)
    - Node  = addWaiter(Node.SHARED)   // 向双向队列尾部添加节点
        - Node pred = tail
        - if (pred != null)      // 非头结点
            - node.prev = pred   // 先设置上一个节点防止插入失败
            - compareAndSetTail(pred, node)   // CAS插入
                - 失败：enq(final Node node)
                    - 自旋 + CAS 不断插入
    - 获取成功则将自己设为头结点，如果后继节点是共享类型的则唤醒


------------- 8.ReentrantLock --------
synchronized的比较

ReentrantReadWriteLock




------------- 9.Countdownlatch & Cyclicbarrier ------
CountdownLatch:
1.new CountDownLatch(count) -- AQS.state=count
2.countDown() -- AQS.releaseShared(1)
   -tryReleaseShared(1) 尝试释放
     - c = getState()
        - c为0即所有的state都已经释放
        - 否则自旋 + CAS 设置新的state = state -1
3.await()
  - AQS.acquireSharedInterruptibly()
    - tryAcquireShared(arg)
      - (getState() == 0) ? 1 : -1    即判断当前是否为0
         - doAcquireSharedInterruptibly(1)
           - 1.Node = addWaiter(Node.SHARED)   将当前线程包装成一个 Node 对象，加入到 AQS 的队列尾部
           - 2.Node = node.predecessor()       如果他前面的 node 是 head ，便可以尝试获取锁了
           - 3.如果不是，则阻塞等待，调用的是 LockSupport.park(this);
  -AQS.tryAcquireSharedNanos(arg,nanos)    中断 + 超时

4.





ThreadPoolExecutor
ThreadLocal








