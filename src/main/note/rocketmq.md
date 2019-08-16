<!--Rocket 归档总结-->

#### 0.架构


#### 1.分布式事务 
主要在Producer实现了 executeLocalTransaction 和 checkLocalTransaction 的执行和查看事务的两方面接口
  1. Producer事务消息 "TRAN_MSG"=true; PGROUP=getProducerGroup 前者是为了识别事务消息后者是为了回写的时候找到group的channel
  2. Broker#SendMessageProcessor 保存事务消息到 HALF_TOPIC 队列并设置real_topic, queueid=0
  3. Producer执行本地事务	-- 在整个本地过程可能涉及很多表尤其资金方面建议提供一张transaction
  4. Broker#EndTransactionProcessor 建立 HALF_TOPIC 一一对应的 HALF_OP_TOPIC 并如果commit则新建原始topic队列进行消费
  5. Broker#TransactionMessageCheckService 根据比较两个队列找不到未回差消息进行回查（难）
  6. 根据回查结果继续 Broker#EndTransactionProcessor

桥接模式：
1、定义一个桥接口，使其与一方绑定，这一方的扩展全部使用实现桥接口的方式
2、定义一个抽象类，来表示另一方，在这个抽象类内部要引入桥接口，而这一方的扩展全部使用继承该抽象类的方式

Dubbo 分布式调用
1.TCC( try- confirm -cancel)

#### 2.消息存储
**内存映射**
1. DirectByteBuffer 
`ByteBuffer byteBuffer = ByteBuffer.allocateDirect(fileSize)`
> DirectBuffer, 本身在java堆的 malloc()/mmap()分配出来的内存是用户态(只读，数据段，堆，文件映射段，栈)且在堆和文件映射段


2. MappedByteBuffer 
` this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
  this.mappedByteBuffer = this.fileChannel.map(MapMode.READ_WRITE, 0, fileSize); `
> fileChannel.write(byteBuffer), 基于通道Channel分配在堆外通过DirectByteBuffer

3. pageCache, 直接内存
虚拟内存，堆内存，堆外内存，用户态内存，内核态内存(关联物理内存),Pointer
堆外内存 --->(commit) 内存映射Buffer --->flush#force() 磁盘

**mmap**
> mmap是一种将文件映射到虚拟内存的技术,
可以将文件在磁盘位置的地址和在虚拟内存中的虚拟地址通过映射对应起来，之后就可以在内存这块区域进行读写数据，而不必调用系统级别的read,write这些函数从而提升IO操作性能。
同时 mmap 将一个程序指定的文件映射进虚拟内存(Virtual Memory) 对文件的读写就变成了对内存的读写，能充分利用Page Cache(这里的pageCache指的是系统缓存部分)
但是顺序IO时，读和写的区域都是被OS智能Cache过的热点区域，不会产生大量缺页中断，文件的IO几乎等同于内存的IO，性能当然就上去了
当用户态应用使用 `MADV_WILLNEED` 命令执行 `madvise()` 系统调用时，它会通知内核，某个文件内存映射区域中的给定范围的文件页不久将要被访问

**缺页中断**
> 当进程访问的虚拟地址在页表查不到时系统就会产生缺页中断，通知内核空间分配物理内存，更新进程页表，最后在返回用户空间，恢复进程运行。
linux默认分页为4K，可以想象读一个1G的消息存储文件要发生多少次中断

**零拷贝**



#### 3.PULL长轮询 &短轮询	longPollingEnable
	BrokerController.start()
		- pullRequestHoldService.start()
			- getBrokerConfig().isLongPollingEnable() -- this.waitForRunning(5 * 1000) ||  this.waitForRunning(1000);
			- this.checkHoldRequest();
				- notifyMessageArriving(topic, queueId, offset)
					- ManyPullRequest mpr = this.pullRequestTable.get(key);			// 从 pullRequestTable
					- PullMessageProcessor.executeRequestWhenWakeup(Channel, RemotingCommand request) 
						- RemotingCommand response = PullMessageProcessor.this.processRequest(channel, request, false)		


#### 4.PULL&PUSH 消息消费



#### 5.定时消息 &延迟消息 
定时消息：Producer 将消息发送到 MQ 服务端，但并不期望这条消息立马投递，而是推迟到在当前时间点之后的某一个时间投递到 Consumer 进行消费，该消息即定时消息。
延迟消息：Producer 将消息发送到 MQ 服务端，但并不期望这条消息立马投递，而是延迟一定时间后才投递到 Consumer 进行消费，该消息即延时消息。
消息回朔，即回去重新消费
失败延迟策略：
private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";

对于消费失败的消息如拉取了100条2101-2200但是在消费2101失败后面99条成功的情况下回写给broker
defaultMQPushConsumer.getConsumeTimeout();		  //  每次开始消费之前每15min清楚一次 ConsumeMessageConcurrentlyService#cleanExpireMsg()#

CONSUME_FROM_LAST_OFFSET //默认策略，从该队列最尾开始消费，即跳过历史消息
CONSUME_FROM_FIRST_OFFSET //从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
CONSUME_FROM_TIMESTAMP//从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
"sh mqadmin brokerStatus"  查看当前的消费进度


#### 7.ACK进度保存 
   "offsetTable":{
                "TopicTest@please_rename_unique_group_name_4":{0:533,1:533,2:533,3:532,4:60,5:60,6:60,7:62,8:62,9:61,10:21,11:25,12:23,13:23,14:21,15:20
                },
                "TopicTest@please_rename_unique_group_name_3":{0:0,1:0,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0,15:0
                },
                "%RETRY%please_rename_unique_group_name_4@please_rename_unique_group_name_4":{0:0
                },
                "%RETRY%group_name_test@group_name_test":{0:0
                },
                "%RETRY%please_rename_unique_group_name_3@please_rename_unique_group_name_3":{0:0
                },
                "TopicTest@group_name_test":{0:535,1:535,2:535,3:534,4:62,5:62,6:61,7:64,8:64,9:63,10:23,11:26,12:25,13:25,14:23,15:22
                }
        }
	
			

#### 8.顺序消费 
顺序消费时对broker加锁的原因; 顺序拉取时再次从broker确认offset?;  
   1.负载取PullRequest. rebalanceByTopic
       a.<mq,pq>本次未负载到但上次负载消费的MQ 在broker解锁（还未消费完怎么办？）；如果存在判断拉取消息时间间隔是否过长 也解锁
       b.对负载到的mq broker加锁同时processQueue.setLocked(true);
   2.拉取消息pullMessage.
     processQueue.isLocked():
	   a.未加锁的 processQueue->executePullRequestLater;重新放回pullRequestQueue等待拉取
	   b.加锁的 offset=computePullFromWhere(pullRequest.getMessageQueue()) 重新计算offset
   3.消费消息 ConsumeMessageOrderlyService.
	   a.启动lockMQPeriodically(); 把processQueueTable的在broker端lockBatchMQ。消费的时候才锁broker
	   b.PullCallback()拉取后放到ConsumeRequest线程池；

##### 1、一个队列同时只被一台机器消费
RebalanceService在重新分配完队列后有可能processQueue中的msgTreeMap并没有消费完成也就是在途数据即可能同时存在两台机器消费同一个队列的消息这个在顺序消费中显然是不允许的
所以rocketmq设置了在broker加队列锁并且锁默认60s失效（为什么失效，便于当机器宕机时，能被分配给其他在线的消费者消费），20s一次负载 isExpired()
只有获得锁的ProcessQueue才能执行ConsumeRequest线程

##### 2、上一个队列id没有消费完成，则不能消费下一个线程
ConsumeRequest是多线程消费的，显然这个在顺序消费中也是有问题的。
rocketmq是怎么解决的呢？
    1.ConsumeRequest 运行时加了队列的内存锁 mqLockTable<MessageQueue, Object>即同一个MQ拉取的时候如果上一个Pull还没消费
    2.ConsumeRequest 在顺序消费中，是从msgTreeMap中取offset小的消息先消费

##### 3、顺序消费失败
   SUSPEND_CURRENT_QUEUE_A_MOMENT 中断当前
   开始重新消费
       1.默认最大次数Integer.MAX_VALUE 一直重试？
       2.consumingMsgOrderlyTreeMap.remove(msg.getQueueOffset()) msgTreeMap.put(msg.getQueueOffset(), msg)
       3.默认1s submitConsumeRequestLater重新丢到线程池并行消费

##### 4、MQ还没消费完又被重新负载了怎么办(负载的时候20s次会解锁)
   解锁的过程会判断treeMap是否为空 否则延迟20s解锁
   消费的时候判断 processQueue.isDropped() 且到broker解锁是判断 processQueue.getLockConsume().lock();

Broker服务端锁 RebalanceLockManager#tryLockBatch(String group, Set<MessageQueue> mqs, String clientId) LOCK_BATCH_MQ
	-- this.isLocked(group, mq, clientId)	  											// 该mq是否被锁到 mqLockTable
		- boolean locked = lockEntry.isLocked(clientId);								// 是否已经被当前请求的clientId锁到
		- if(locked) lockEntry.setLastUpdateTimestamp(System.currentTimeMillis());      // 如果是更新当前时间
	- lockedMqs.add(mq)   		// 如果是当前clientId锁到直接加入返回mqSet
	- notLockedMqs.add(mq);
		- 判断是 null == lockEntry ||lockEntry.isLocked(clientId) ||lockEntry.isExpired()  
		- 否则说明该mq还在被锁 不能使用
		
		
#### 9.channel & tag

