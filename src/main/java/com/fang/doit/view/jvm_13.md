#### 1.栈内存溢出
 溢出异常 StackOverFlowError 这是环境原因造成的error异常用throwable捕获。（还有 OutOfMemoryError）

#### 2.JVM的内存结构，Eden和Survivor比例
8:1:1

#### 3.JVM内存为什么要分成新生代，老年代，持久代。新生代中为什么要分为Eden和Survivor。
分代："朝生夕死"的区域进行回收
复制算法前提下分成2个Survivor，解决了一个Survivor Minor GC后碎片化


#### 4.JVM中一次完整的GC流程是怎样的，对象如何晋升到老年代，说说你知道的几种主要的JVM参数。 -- 2021.04.12
-XX:PretenureSizeThreshold  对象的大小大于此值就会绕过新生代
-XX:MaxTenuringThreshold    对象的年龄增加到一定的临界值时，就会晋升到老年代


#### 5.你知道哪几种垃圾收集器，各自的优缺点，重点讲下cms和G1，包括原理，流程，优缺点。垃圾回收算法的实现原理。 -- 2021.04.12
1. Serial 单线程收集器  新生代 复制算法
2. ParNew 并发收集器  新生代  复制算法
3. Parallel Scavenge 吞吐量收集器  新生代 复制算法
4. CMS 低停顿收集器  老年代   标记-清楚算法
5. G1  新/年代  标记-整理算法


#### 6.当出现了内存溢出，你怎么排错。 -- 2021.04.12
> 1. 打印dump日志
> 2. 



#### 7.JVM内存模型的相关知识了解多少，比如重排序，内存屏障，happen-before，主内存，工作内存等。
JVM内存结构：堆，栈，方法区，常量池
JVM内存模型：
   1.由于CPU为了均衡和内存速度差异进行了缓存多缓存之间存在可见性问题及必要指令优化带来的有序性问题
   2.强内存模型：由硬件提供；弱内存模型：特殊指令（内存屏障） 刷新CPU缓存的数据到内存中，保证这个写操作能够被其它CPU可见
Java内存模型规范了JVM如何提供按需禁用缓存和编译优化的方法，即volatile，synchronized，final及Happens-Before

synchronized: 保证了线程在同步块之前或者期间写入动作，对于后续进入该代码块的线程是可见的(不考虑互斥)  
    1.在一个线程退出同步块时，线程释放monitor对象，它的作用是把CPU缓存数据（本地缓存数据）刷新到主内存中，从而实现该线程的行为可以被其它线看到。2.在其它线程进入到该代码块时，需要获得monitor对象，它在作用是使CPU缓存失效，从而使变量从主内存中重新加载，然后就可以看到之前线程对该变量的修改（这里需要注意的是同一个monitor对象）
final: 引用不可变即可保证了可见性
volatile:禁用CPU缓存和synchronized类似；禁止重排序
happen-before：前面的操作对后面的操作可见的
    1.单线程下前面Happens-Before后面操作
    2.volatile变量的前操作Happens-Before后面变量的读操作（可见性）
    3.传递性
    4.管程中锁规则：管程是一种同步原语即 synchronized.可以理解为synchronized释放锁后修改的变量对后面加锁的管程可见
    5.线程start规则：子线程可以看见启动前的主线程操作
    6.join()规则：B.join()那么B的所有操作对A可见


#### 8.简单说说你了解的类加载器，可以打破双亲委派么，怎么打破。
类加载过程
1. 加载，classLoader会把jvm编译完成的.class二进制文件加载到内存
2. 连接
      验证，确保Class文件的字节流中包含的信息是否符合当前虚拟机的要求
      准备，为类的静态变量分配内存，将其初始化为默认值。如static int=0
      解析，把类中的符号引用转换为直接引用
3. 初始化
    以下情况不会触发初始化：
    通过子类引用父类的静态字段，只会触发父类的初始化，而不会触发子类的初始化。
    定义对象数组，不会触发该类的初始化。
    常量在编译期间会存入调用类的常量池中，本质上并没有直接引用定义常量的类，不会触发定义常量所在的类。
    通过类名获取Class对象，不会触发类的初始化。
    通过Class.forName加载指定类时，如果指定参数initialize为false时，也不会触发类初始化，其实这个参数是告诉虚拟机，是否要对类进行初始化。
    通过ClassLoader默认的loadClass方法，也不会触发初始化动作

打破双亲委派：
1. Thread.currentThread().getContextClassLoad() 当发生父级类加载路径下的类需要回调用户代码的时候(JDBC#DriverManager Driver?) 以此使用AppClassLoader
2. JDK1.2为了向前兼容 使用loadClass()调用自己的???


#### 9.讲讲JAVA的反射机制
Method method = Proxy.class.getDeclaredMethod("run");
委派实现 DelegatingMethodAccessorImpl
    本地实现 NativeMethodAccessorImpl# native Object invoke0
    动态实现 使用invoke指令调用目标方法，无需Java-- C++ -- Java切换但生成字节码耗时
时间花费
1.可变参数尽量使用Integer代理int,避免拆箱装箱
2.如果使用Object[]直接作为参数，原本反射调用被内联了从而使得即使编译中的逃逸方法将原本新建的Object数组判定为不逃逸对象
3.关闭反射的Inflation机制取消委派并直接使用动态实现
    -Djava.lang.Integer.IntegerCache.high=128
    -Dsun.reflect.noInflation=true 
    方法内联  
方法内联：编译器在编译一个方法时将某个方法调用的目标方法也纳入编译范围内并用其代替原方法调用的过程   

#### 10.你们线上应用的JVM参数有哪些。
-Xms / -Xmx
-XX:PermSize / -XX:MaxPermSize
-XX:+UseConcMarkSweepGC / -XX:+UseCMSCompactAtFullCollection
-XX:CMSMaxAbortablePrecleanTime
-XX:+CMSClassUnloadingEnabled
-XX:+UseCMSInitiatingOccupancyOnly 
-XX:CMSInitiatingOccupancyFraction=80
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=...
-XX:MaxDirectMemorySize
-XX:SurvivorRatio=10
-XX:ParallelGCThreads=${CPU_COUNT}  CPU_COUNT="$(grep -c 'cpu[0-9][0-9]*' /proc/stat)"

#### 11.g1和cms区别,吞吐量优先和响应优先的垃圾收集器选择。


#### 12.怎么打出线程栈信息。
jstat -p 

#### 13.请解释如下jvm参数的含义：
-server 
-Xms512m 
-Xmx512m 
-Xss1024K
-XX:PermSize=256m 
-XX:MaxPermSize=512m 
-XX:MaxTenuringThreshold=20
-XX:CMSInitiatingOccupancyFraction=80 触发老年代回收垃圾的比例
-XX:+UseCMSInitiatingOccupancyOnly
