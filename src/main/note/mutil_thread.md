
#### ��������
> CPU�����˻��� �Ծ������ڴ���ٶȲ���	(��CPU���浼���ڴ�ɼ�������)
> ����ϵͳ�����˽����߳��Է�ʱ����CPU��������CPU��I/O�豸���ٶȲ��� (�߳��л�������ԭ��������)
> ��������Ż�ָ��ִ�д����ƵĻ����ܹ��õ����Ӻ��������  (������double check�� new�Ĺ��̿����ȵ�ַ��ֵ�ٳ�ʼ����������������??? �������ԭ���Զ����������)

#### �ɼ��� & ������
`Happens-Before` �Ĳ����Ժ����ǿɼ��ģ�����������Ż������Ż���һ������Happens-Before
1. ����˳���Թ���
2. volatile���򣬶�һ��volatileд����Happens-Before�ں�������������Ķ�����
3. ������
4. �ܳ������Ĺ���
5. start����

> volatile�����������Ա�֤�Լ��ı����ɼ��ԣ����ܱ�֤��д��volatile����д����֮ǰ�Ĳ���Happens-Before��д��volatile����������֮�����Щ����
> �����������volatile������д��Ͷ�ȡ�������̣�
> 1. ���ȣ�volatile�����Ĳ������ֹ��������ͨ�����Ĳ�������������������������л��ֹinitialized = true������������д������������(����������Ĵ���֮���ǿ����������)������ᵼ�³���������volatile������д����������һ����׼�ߣ�����������֮�󣬲���֮ǰ�Ĵ�����û�������򣬷�������������֮��ǰ��Ĳ���������ɲ����ɺý����
> 2. Ȼ����volatile����д����������A�̻߳��volatile�����������д����֮ǰ����Щ������ִ�н��һ��ͬ�������ڴ��С�
> 3. ��󣬵�B�̶߳�ȡvolatile����ʱ��B�̻߳�ʹ�Լ���CPU����ʧЧ�����´����ڴ��ȡ���������ֵ������������volatile����������д��volatile����д����֮ǰ����Щ���������������B�̸߳�֪����Ҳ������������е�initialized��configOptions����������ֵ���������߳�B��֪��
volatileд������ happens-before

#### ԭ����
һ�������������Դ -- Account.class --> ����
��Ҫ�ѿɱ���󵱳�һ���� ��Ϊ��һ��������ʱ���õ���ֵ�Ѿ�����һ���ı���

#### ����
a.����  ������ԴX��Yֻ�ܱ�һ���߳�ռ��  --- �����������߳�֮���Ȼ���ڻ���
b.ռ���ҵȴ�  �߳�T1�Ѿ�ȡ�ù�����ԴX���ڵȴ�������ԴY��ʱ�򣬲��ͷŹ�����ԴX   --- һ�����������е���Դ***
c.������ռ  �����̲߳���ǿ����ռT1ռ�е���Դ								    --- �����ͷ�
d.ѭ���ȴ�  �߳�T1�ȴ��߳�T2ռ�е���Դ���߳�T2�ȴ��߳�T1ռ�е���Դ����ѭ���ȴ�  --- ����������Դ

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





















