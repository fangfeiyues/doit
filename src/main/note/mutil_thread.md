
#### ��������
> 1. CPU�����˻��� �Ծ������ڴ���ٶȲ���	(��CPU���浼���ڴ�ɼ�������)
> 2. ����ϵͳ�����˽����߳��Է�ʱ����CPU��������CPU��I/O�豸���ٶȲ��� (�߳��л�������ԭ��������)
> 3. ��������Ż�ָ��ִ�д����ƵĻ����ܹ��õ����Ӻ��������  (������double check�� new�Ĺ��̿����ȵ�ַ��ֵ�ٳ�ʼ����������������??? �������ԭ���Զ����������)


#### ���߳� & ���߳�
����ʵ�����ݵ�����ִ���ۼƲ����������ʱ���ٶȻ�ȴ����ۼ�������Ҫ����Ϊ�̵߳Ŀ����ʹ�����
> �鿴�߳������ģ�vmstat 10 1  ÿ10s�ɼ�һ��

procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----  
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st  
 2  0      0 143044 146648 932064    0    0     0     6   12    4  1  0 99  0  0  
 0  0      0 143044 146648 932096    0    0     0     0  227  513  0  0 100  0  0  
 0  0      0 143044 146648 932096    0    0     0     0  198  475  0  0 100  0  0  
 0  0      0 143044 146648 932096    0    0     0     0  202  479  0  0 100  0  0  
 0  0      0 143044 146648 932096    0    0     0     0  210  486  1  1 98  0  0  
> cs ÿ���������л�����
 
�Ų��������л�����
1. jstack�����ӡdump�߳���Ϣ
2. ͳ�������̶߳��ǳ���ʲô״̬
> grep java.lang.Thread.State dump11 | awk '{print $2$3$4$5}' | sort | uniq -c 

3. �鿴�߳���־�����Ǵ���WAITING״̬�� ������һЩJBOSS�Ĺ����߳�
4. �ҵ�JBOSS�Ĺ����߳�������Ϣ�������߳������������ĵ��л�


#### �����߳����� & ���ݿ�����
1. ����  ������ԴX��Yֻ�ܱ�һ���߳�ռ��  --- �����������߳�֮���Ȼ���ڻ���
2. ռ���ҵȴ�  �߳�T1�Ѿ�ȡ�ù�����ԴX���ڵȴ�������ԴY��ʱ�򣬲��ͷŹ�����ԴX   --- һ�����������е���Դ***
3. ������ռ  �����̲߳���ǿ����ռT1ռ�е���Դ								    --- �����ͷ�
4. ѭ���ȴ�  �߳�T1�ȴ��߳�T2ռ�е���Դ���߳�T2�ȴ��߳�T1ռ�е���Դ����ѭ���ȴ�  --- ����������Դ 
 
#### volatile
`Happens-Before` �Ĳ����Ժ����ǿɼ��ģ�����������Ż������Ż���һ������Happens-Before
1. ����˳���Թ���
2. volatile���򣬶�һ��volatileд����Happens-Before�ں�������������Ķ�����
3. ������
4. �ܳ������Ĺ���
5. start����

volatile�����������Ա�֤�Լ��ı����ɼ��ԣ����ܱ�֤��д��volatile����д����֮ǰ�Ĳ���Happens-Before��д��volatile����������֮�����Щ����


#### �ڴ�ģ��


#### ԭ����
һ�������������Դ -- Account.class --> ����
��Ҫ�ѿɱ���󵱳�һ���� ��Ϊ��һ��������ʱ���õ���ֵ�Ѿ�����һ���ı���


#### �ȴ�-֪ͨ
while(!actr.apply(this, target)) �� --> �ȴ�֪ͨ
  wait()/notifyAll()       Sounds good! signall can avoid dead lock better
  CountDownLatch2 -- wait() / countDown()

  
#### �ܳ�Monitor ������
����������������������⣺���� & ͨ�š�Lock����������� Condition����ܳ�����
Lock(ReentrantLock) & Synchronized��
	Sync�޷��ƻ�������"��ռ�����ƻ���Դ"����,ֻ��һֱ�ȴ���
	Lock����ж���ӦlockInterruptibly()��֧�ֳ�ʱtryLock(long time, TimeUnit unit)��֧�ַ������Ļ�ȡ��tryLock()
    �ɼ���Happens-before, Sync�Ľ���Happens-before�ں�����������ļ���
Semaphore: �������̷߳���һ���ٽ���


#### ���߳��̳߳�
Ϊʲô�Ƕ��̣߳������ӳ٣���������� --> �Ż��㷨�ͷ���Ӳ�����ܡ����߾�������I/O�����ʺ�CPU�����ʣ������߳���Ҫ������ƽ��CPU��I/O
CPU�ܼ� = CPU��
I/O�ܼ���= CPU��*(1+ I/Oʱ�� / CPUʱ��)

ThreadPoolExecutor(
int corePoolSize,
int maximumPoolSize,
long keepAliveTime,
TimeUnit timeUnit,
BlockingQueue<Runnable> workQueue,
ThreadFactory threadFactory,
RejectedExecutionHandler handler
)
�ͳػ�������ֱ����ȡacquire()
	

#### Semaphore�ź���

#### ��д��
1.�������߳�ͬʱ���������
2.ֻ����һ���߳�д�������


#### �첽�� CompletableFuture
dubbo���첽�����죺CompletableFuture.completedFuture(result);
1.�������й�ϵ thenApply,thenAccept,thenRun,
	CompletableFuture<String> f0 = 
	  CompletableFuture.supplyAsync(
		() -> "Hello World")      		//��
	  .thenApply(s -> s + " QQ")        //��
	  .thenApply(String::toUpperCase);  //��
	System.out.println(f0.join());
	// ������ ===> HELLO WORLD QQ
2.���� AND ��۹�ϵ
3.���� OR ��۹�ϵ
4.�쳣����


#### Imm

---
#### CAS
java.util.concurrent����ȫ������CAS��
CAS���������������ڴ�ֵV���ɵ�Ԥ��ֵA��Ҫ�޸ĵ�ֵB�����ҽ���Ԥ��ֵA���ڴ�ֵV��ͬʱ�����ڴ�ֵ�޸�ΪB������true������ʲô������������false
CASҲ��ͨ��Unsafe(ֱ�Ӳ�������ϵͳ��Ӳ��)ʵ�ֵ� ����CAS����Ӳ������Ĳ��������Ч�ʻ��һЩ





















