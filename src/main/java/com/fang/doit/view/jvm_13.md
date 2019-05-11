#### 1.什么情况下会发生栈内存溢出
1.递归

#### 2.JVM的内存结构，Eden和Survivor比例
8:1

#### 3.JVM内存为什么要分成新生代，老年代，持久代。新生代中为什么要分为Eden和Survivor。
复制算法前提下分成2个Survivor，解决了一个Survivor Minor GC后碎片化

#### 4.JVM中一次完整的GC流程是怎样的，对象如何晋升到老年代，说说你知道的几种主要的JVM参数。
-XX:PretenureSizeThreshold  对象的大小大于此值就会绕过新生代
-XX:MaxTenuringThreshold    对象的年龄增加到一定的临界值时，就会晋升到老年代


#### 5.你知道哪几种垃圾收集器，各自的优缺点，重点讲下cms和G1，包括原理，流程，优缺点。垃圾回收算法的实现原理。



#### 6.当出现了内存溢出，你怎么排错。


#### 7.JVM内存模型的相关知识了解多少，比如重排序，内存屏障，happen-before，主内存，工作内存等。


#### 8.简单说说你了解的类加载器，可以打破双亲委派么，怎么打破。


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


#### 11.g1和cms区别,吞吐量优先和响应优先的垃圾收集器选择。


#### 12.怎么打出线程栈信息。


#### 13.请解释如下jvm参数的含义：
-server 
-Xms512m 
-Xmx512m 
-Xss1024K
-XX:PermSize=256m 
-XX:MaxPermSize=512m 
-XX:MaxTenuringThreshold=20
-XX:CMSInitiatingOccupancyFraction=80 
-XX:+UseCMSInitiatingOccupancyOnly
