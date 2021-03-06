#### 1.JAVA中的几种基本数据类型是什么，各自占用多少字节
byte     1字节
boolean    1字节
char    2字节
short   2字节
int     4字节
float   4字节
double  8字节
long    8字节

#### 2.String类能被继承吗，为什么
> String类继承final类不能被继承。
> 'abc'字符串直接存放在方法区的常量池。常为了避免频繁的创建和销毁对象造成系统性能的浪费实现了对象的共享


#### 3.String，StringBuffer，StringBuilder的区别
1. String: 线程不安全
2. StringBuffer: 线程安全  
3. StringBuilder: 线程不安全

 
#### 4.ArrayList和LinkedList有什么区别
1. ArrayList 数组
2. LinkedList 双向链表
3. CopyOnWriteArrayList。用volatile Object[] array写副本的方式及时更新数据给读线程但写保证单线程。在读多写少下适合ArrayList变得安全


#### 5.讲讲类的实例化顺序，比如父类静态数据，构造函数，字段，子类静态数据，构造函数，字 段，当new的时候他们的执行顺序
父类静态变量、 
父类静态代码块、 
子类静态变量、 
子类静态代码块、 
父类非静态变量（父类实例成员变量）、 
父类构造函数、 
子类非静态变量（子类实例成员变量）、 
子类构造函数
https://blog.csdn.net/mad1989/article/details/20793539

#### 6.用过哪些Map类，都有什么区别，HashMap是线程安全的吗,并发下使用的Map是什么，他们内部原理分别是什么，比如存储方式，hashcode，扩容，默认容量等。
1. HashMap线上出现问题
2. HashMap扩容。达到初始容量*负载因子 -- 数组桶的长度扩大一倍 -- 数据的transfer()


#### 7.JAVA8的 ConcurrentHashMap 为什么放弃了分段锁，有什么问题吗，如果你来设计，你如何设计 -- 2021.04.08
ConcurrentHashMap


#### 8.有没有有顺序的Map实现类，如果有，他们是怎么保证有序的
TreeMap 红黑树排序处理


#### 9.抽象类和接口的区别，类可以继承多个类么，接口可以继承多个接口么,类可以实现多个接口么
抽象类可以实现公共父类方法，而接口不能
类单继承多实现


#### 10.继承和聚合的区别在哪
类继承另外的一个类的功能
聚合体现的是整体与部分、拥有的关系


#### 11.IO模型有哪些，讲讲你理解的nio ，他和bio，aio的区别是啥，谈谈reactor模型 -- 2021.04.08
IO模型 
> 1. blocking IO   
> 2. non-blocking IO   
> 3. IO multiplexing(事件驱动IO) select/epoll 这个function会不断的轮询所负责的所有socket
> 4. async IO


#### 12.反射的原理，反射创建类实例的三种方式
原理  NativeMethodAccessorImpl/(new MethodAccessorGenerator()).generateMethod 15作为一个岭
不足  native版的反射调用则无法被有效内联，因而调用开销无法随程序的运行而降低
三种方式
1.class1 = Person.class;
2.class2 = p1.getClass();
3.class3 = Class.forName("cn.fang.Person");

开启noInflation第一次就会使用字节码
  public MethodAccessor newMethodAccessor(Method var1) {
        checkInitted();
        if (noInflation && !ReflectUtil.isVMAnonymousClass(var1.getDeclaringClass())) {
            return (new MethodAccessorGenerator()).generateMethod(var1.getDeclaringClass(), var1.getName(), var1.getParameterTypes(), var1.getReturnType(), var1.getExceptionTypes(), var1.getModifiers());
        } else {
            NativeMethodAccessorImpl var2 = new NativeMethodAccessorImpl(var1);
            DelegatingMethodAccessorImpl var3 = new DelegatingMethodAccessorImpl(var2);
            var2.setParent(var3);
            return var3;
        }
    }
    
否则NativeMethodAccessorImpl.java 15次本地使用之后开始字节码
 if (++this.numInvocations > ReflectionFactory.inflationThreshold() && !ReflectUtil.isVMAnonymousClass(this.method.getDeclaringClass())) {
            MethodAccessorImpl var3 = (MethodAccessorImpl)(new MethodAccessorGenerator()).generateMethod(this.method.getDeclaringClass(), this.method.getName(), this.method.getParameterTypes(), this.method.getReturnType(), this.method.getExceptionTypes(), this.method.getModifiers());
            this.parent.setDelegate(var3);
        }
    
    
#### 13.反射中Class.forName和ClassLoader.loadClass()区别 
> 1. class.forName()除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块，还会执行给静态变量赋值的静态方法
> 2. classLoader.loadClass() 只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块


#### 14.描述动态代理的几种实现方式，分别说出相应的优缺点 -- 2021.04.08
> 1. ASM和JAVAASSIST字节码生成方式不相上下，都很快，是CGLIB的5倍。 
> 2. CGLIB次之，是JDK自带的两倍。 
> 3. JDK自带的再次之，因JDK1.6对动态代理做了优化，如果用低版本JDK更慢，要注意的是JDK也是通过字节码生成来实现动态代理的，而不是反射。 
> 4. JAVAASSIST提供者动态代理接口最慢，比JDK自带的还慢。 
(这也是为什么网上有人说JAVAASSIST比JDK还慢的原因，用JAVAASSIST最好别用它提供的动态代理接口，而可以考虑用它的字节码生成方式) 

差异原因： 
各方案生成的字节码不一样， 
像JDK和CGLIB都考虑了很多因素，以及继承或包装了自己的一些类， 
所以生成的字节码非常大，而我们很多时候用不上这些， 
而手工生成的字节码非常小，所以速度快， 
具体的字节码对比，后面有贴出，可自行分析。 

最终选型： 
最终决定使用JAVAASSIST的字节码生成代理方式， 
虽然ASM稍快，但并没有快一个数量级， 
而JAVAASSIST的字节码生成方式比ASM方便， 
JAVAASSIST只需用字符串拼接出Java源码，便可生成相应字节码， 而ASM需要手工写字节码。 


#### 15.动态代理与cglib实现的区别


#### 16.为什么CGlib方式可以对接口实现代理


#### 17.final的用途
> 1.不可变的是变量的引用而非引用指向对象的内容
> 2.被final修饰的方法，JVM会尝试为之寻求内联这对于提升Java的效率是非常重要的。因此假如能确定方法不会被继承，那么尽量将方法定义为final的
> 3.被final修饰的常量，在编译阶段会存入调用类的常量池中


#### 18.写出三种单例模式实现
> 1.懒汉式  
> 2.饿汉式
> 3.Holder  private static class Holder {  private static final Singleton SINGLETON = new Singleton(); }
> 4.Double Check (volatile防止重排序问题)
> 5.枚举 《Effective Java》

#### 19.如何在父类中为子类自动完成所有的hashcode和equals实现？这么做有何优劣
在应用程序的执行期间，只要对象的equals方法的比较操作所用到的信息没有被修改，那么对这同一个对象调用多次，hashCode方法都必须始终如一地返回同一个整数。
在同一个应用程序的多次执行过程中，每次执行所返回的整数可以不一致。

如果两个对象根据equals()方法比较是相等的，那么调用这两个对象中任意一个对象的hashCode方法都必须产生同样的整数结果。
如果两个对象根据equals()方法比较是不相等的，那么调用这两个对象中任意一个对象的hashCode方法，则不一定要产生相同的整数结果。
         给不相等的对象产生截然不同的整数结果，有可能提高散列表的性能。

#### 20.请结合OO设计理念，谈谈访问修饰符public、private、protected、default 在应用设计中的作用


#### 21.深拷贝和浅拷贝区别
> 深拷贝：
> 浅拷贝：

#### 22.数组和链表数据结构描述，各自的时间复杂度
> 数组：不连续的内存空间 
> 链表：

#### 23.error和exception的区别，CheckedException，RuntimeException的区别
Exception & Error 都是继承Throwable类，在Java中只有Throwable类型的实例才可以被抛出或捕获
> Exception：java中可预先知道的异常情况，可以捕获异常并处理
> Error: 不可捕获异常，这种异常发生经常就会直接导致JVM不可用 如OutOfMemoryError，NoClassDefFoundError

NoClassDefFoundError & ClassNotFoundException 区别：
> NoClassDefFoundError是类依赖的class或jar不存在
> ClassNotFoundException 是在forName()查找的时候找不到


#### 24.请列出5个运行时异常


#### 25.在自己的代码中，如果创建一个java.lang.String类，这个类是否可以被类加载器加载？为什么
> 不能。在双亲委派模式下java.*的类会一直到父加载器Bootstrap ClassLoader 此时发现已经有一个String的类


#### 26.说一说你对java.lang.Object对象中hashCode和equals方法的理解。在什么场景下需要重新实现这两个方法
> 对象之间通过某些属性进行比较

#### 27.在jdk1.5中，引入了泛型，泛型的存在是用来解决什么问题


#### 28.这样的a.hashcode() 有什么用，与a.equals(b)有什么关系
> 会先判断a.hashCode()是否和b.hashCode()相同，不相同在判断两者的equals()方法

#### 29，有没有可能2个不相等的对象有相同的hashcode,
> hash算法下很有可能

#### 30.Java中的HashSet内部是如何工作的
> hashCode(): 
> equals():

#### 31.什么是序列化，怎么序列化，为什么序列化，反序列化会遇到什么问题，如何解决
> 序列化是将对象的状态信息转换为可以存储或传输的形式的过程
> 实现数据的持久化；利用序列化远程通信

#### 32.java8的新特性



