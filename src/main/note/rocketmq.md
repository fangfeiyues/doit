<!--Rocket �鵵�ܽ�-->

#### 0.�ܹ�


#### 1.�ֲ�ʽ���� 
��Ҫ��Producerʵ���� executeLocalTransaction �� checkLocalTransaction ��ִ�кͲ鿴�����������ӿ�
  1. Producer������Ϣ "TRAN_MSG"=true; PGROUP=getProducerGroup ǰ����Ϊ��ʶ��������Ϣ������Ϊ�˻�д��ʱ���ҵ�group��channel
  2. Broker#SendMessageProcessor ����������Ϣ�� HALF_TOPIC ���в�����real_topic, queueid=0
  3. Producerִ�б�������	-- ���������ع��̿����漰�ܶ�������ʽ��潨���ṩһ��transaction
  4. Broker#EndTransactionProcessor ���� HALF_TOPIC һһ��Ӧ�� HALF_OP_TOPIC �����commit���½�ԭʼtopic���н�������
  5. Broker#TransactionMessageCheckService ���ݱȽ����������Ҳ���δ�ز���Ϣ���лز飨�ѣ�
  6. ���ݻز������� Broker#EndTransactionProcessor

�Ž�ģʽ��
1������һ���Žӿڣ�ʹ����һ���󶨣���һ������չȫ��ʹ��ʵ���Žӿڵķ�ʽ
2������һ�������࣬����ʾ��һ����������������ڲ�Ҫ�����Žӿڣ�����һ������չȫ��ʹ�ü̳иó�����ķ�ʽ

Dubbo �ֲ�ʽ����
1.TCC( try- confirm -cancel)

#### 2.��Ϣ�洢
**�ڴ�ӳ��**
1. DirectByteBuffer 
`ByteBuffer byteBuffer = ByteBuffer.allocateDirect(fileSize)`
> DirectBuffer, ������java�ѵ� malloc()/mmap()����������ڴ����û�̬(ֻ�������ݶΣ��ѣ��ļ�ӳ��Σ�ջ)���ڶѺ��ļ�ӳ���


2. MappedByteBuffer 
` this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
  this.mappedByteBuffer = this.fileChannel.map(MapMode.READ_WRITE, 0, fileSize); `
> fileChannel.write(byteBuffer), ����ͨ��Channel�����ڶ���ͨ��DirectByteBuffer

3. pageCache, ֱ���ڴ�
�����ڴ棬���ڴ棬�����ڴ棬�û�̬�ڴ棬�ں�̬�ڴ�(���������ڴ�),Pointer
�����ڴ� --->(commit) �ڴ�ӳ��Buffer --->flush#force() ����

**mmap**
> mmap��һ�ֽ��ļ�ӳ�䵽�����ڴ�ļ���,
���Խ��ļ��ڴ���λ�õĵ�ַ���������ڴ��е������ַͨ��ӳ���Ӧ������֮��Ϳ������ڴ����������ж�д���ݣ������ص���ϵͳ�����read,write��Щ�����Ӷ�����IO�������ܡ�
ͬʱ mmap ��һ������ָ�����ļ�ӳ��������ڴ�(Virtual Memory) ���ļ��Ķ�д�ͱ���˶��ڴ�Ķ�д���ܳ������Page Cache(�����pageCacheָ����ϵͳ���沿��)
����˳��IOʱ������д�������Ǳ�OS����Cache�����ȵ����򣬲����������ȱҳ�жϣ��ļ���IO������ͬ���ڴ��IO�����ܵ�Ȼ����ȥ��
���û�̬Ӧ��ʹ�� `MADV_WILLNEED` ����ִ�� `madvise()` ϵͳ����ʱ������֪ͨ�ںˣ�ĳ���ļ��ڴ�ӳ�������еĸ�����Χ���ļ�ҳ���ý�Ҫ������

**ȱҳ�ж�**
> �����̷��ʵ������ַ��ҳ��鲻��ʱϵͳ�ͻ����ȱҳ�жϣ�֪ͨ�ں˿ռ���������ڴ棬���½���ҳ������ڷ����û��ռ䣬�ָ��������С�
linuxĬ�Ϸ�ҳΪ4K�����������һ��1G����Ϣ�洢�ļ�Ҫ�������ٴ��ж�

**�㿽��**



#### 3.PULL����ѯ &����ѯ	longPollingEnable
	BrokerController.start()
		- pullRequestHoldService.start()
			- getBrokerConfig().isLongPollingEnable() -- this.waitForRunning(5 * 1000) ||  this.waitForRunning(1000);
			- this.checkHoldRequest();
				- notifyMessageArriving(topic, queueId, offset)
					- ManyPullRequest mpr = this.pullRequestTable.get(key);			// �� pullRequestTable
					- PullMessageProcessor.executeRequestWhenWakeup(Channel, RemotingCommand request) 
						- RemotingCommand response = PullMessageProcessor.this.processRequest(channel, request, false)		


#### 4.PULL&PUSH ��Ϣ����



#### 5.��ʱ��Ϣ &�ӳ���Ϣ 
��ʱ��Ϣ��Producer ����Ϣ���͵� MQ ����ˣ�����������������Ϣ����Ͷ�ݣ������Ƴٵ��ڵ�ǰʱ���֮���ĳһ��ʱ��Ͷ�ݵ� Consumer �������ѣ�����Ϣ����ʱ��Ϣ��
�ӳ���Ϣ��Producer ����Ϣ���͵� MQ ����ˣ�����������������Ϣ����Ͷ�ݣ������ӳ�һ��ʱ����Ͷ�ݵ� Consumer �������ѣ�����Ϣ����ʱ��Ϣ��
��Ϣ��˷������ȥ��������
ʧ���ӳٲ��ԣ�
private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";

��������ʧ�ܵ���Ϣ����ȡ��100��2101-2200����������2101ʧ�ܺ���99���ɹ�������»�д��broker
defaultMQPushConsumer.getConsumeTimeout();		  //  ÿ�ο�ʼ����֮ǰÿ15min���һ�� ConsumeMessageConcurrentlyService#cleanExpireMsg()#

CONSUME_FROM_LAST_OFFSET //Ĭ�ϲ��ԣ��Ӹö�����β��ʼ���ѣ���������ʷ��Ϣ
CONSUME_FROM_FIRST_OFFSET //�Ӷ����ʼ��ʼ���ѣ�����ʷ��Ϣ����������broker�ģ�ȫ������һ��
CONSUME_FROM_TIMESTAMP//��ĳ��ʱ��㿪ʼ���ѣ���setConsumeTimestamp()���ʹ�ã�Ĭ���ǰ��Сʱ��ǰ
"sh mqadmin brokerStatus"  �鿴��ǰ�����ѽ���


#### 7.ACK���ȱ��� 
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
	
			

#### 8.˳������ 
˳������ʱ��broker������ԭ��; ˳����ȡʱ�ٴδ�brokerȷ��offset?;  
   1.����ȡPullRequest. rebalanceByTopic
       a.<mq,pq>����δ���ص����ϴθ������ѵ�MQ ��broker��������δ��������ô�죿������������ж���ȡ��Ϣʱ�����Ƿ���� Ҳ����
       b.�Ը��ص���mq broker����ͬʱprocessQueue.setLocked(true);
   2.��ȡ��ϢpullMessage.
     processQueue.isLocked():
	   a.δ������ processQueue->executePullRequestLater;���·Ż�pullRequestQueue�ȴ���ȡ
	   b.������ offset=computePullFromWhere(pullRequest.getMessageQueue()) ���¼���offset
   3.������Ϣ ConsumeMessageOrderlyService.
	   a.����lockMQPeriodically(); ��processQueueTable����broker��lockBatchMQ�����ѵ�ʱ�����broker
	   b.PullCallback()��ȡ��ŵ�ConsumeRequest�̳߳أ�

##### 1��һ������ͬʱֻ��һ̨��������
RebalanceService�����·�������к��п���processQueue�е�msgTreeMap��û���������Ҳ������;���ݼ�����ͬʱ������̨��������ͬһ�����е���Ϣ�����˳����������Ȼ�ǲ������
����rocketmq��������broker�Ӷ�����������Ĭ��60sʧЧ��ΪʲôʧЧ�����ڵ�����崻�ʱ���ܱ�������������ߵ����������ѣ���20sһ�θ��� isExpired()
ֻ�л������ProcessQueue����ִ��ConsumeRequest�߳�

##### 2����һ������idû��������ɣ�����������һ���߳�
ConsumeRequest�Ƕ��߳����ѵģ���Ȼ�����˳��������Ҳ��������ġ�
rocketmq����ô������أ�
    1.ConsumeRequest ����ʱ���˶��е��ڴ��� mqLockTable<MessageQueue, Object>��ͬһ��MQ��ȡ��ʱ�������һ��Pull��û����
    2.ConsumeRequest ��˳�������У��Ǵ�msgTreeMap��ȡoffsetС����Ϣ������

##### 3��˳������ʧ��
   SUSPEND_CURRENT_QUEUE_A_MOMENT �жϵ�ǰ
   ��ʼ��������
       1.Ĭ��������Integer.MAX_VALUE һֱ���ԣ�
       2.consumingMsgOrderlyTreeMap.remove(msg.getQueueOffset()) msgTreeMap.put(msg.getQueueOffset(), msg)
       3.Ĭ��1s submitConsumeRequestLater���¶����̳߳ز�������

##### 4��MQ��û�������ֱ����¸�������ô��(���ص�ʱ��20s�λ����)
   �����Ĺ��̻��ж�treeMap�Ƿ�Ϊ�� �����ӳ�20s����
   ���ѵ�ʱ���ж� processQueue.isDropped() �ҵ�broker�������ж� processQueue.getLockConsume().lock();

Broker������� RebalanceLockManager#tryLockBatch(String group, Set<MessageQueue> mqs, String clientId) LOCK_BATCH_MQ
	-- this.isLocked(group, mq, clientId)	  											// ��mq�Ƿ����� mqLockTable
		- boolean locked = lockEntry.isLocked(clientId);								// �Ƿ��Ѿ�����ǰ�����clientId����
		- if(locked) lockEntry.setLastUpdateTimestamp(System.currentTimeMillis());      // ����Ǹ��µ�ǰʱ��
	- lockedMqs.add(mq)   		// ����ǵ�ǰclientId����ֱ�Ӽ��뷵��mqSet
	- notLockedMqs.add(mq);
		- �ж��� null == lockEntry ||lockEntry.isLocked(clientId) ||lockEntry.isExpired()  
		- ����˵����mq���ڱ��� ����ʹ��
		
		
#### 9.channel & tag

