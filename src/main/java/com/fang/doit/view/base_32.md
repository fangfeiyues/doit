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
final 字段的写操作后插入一个写写屏障，防止某些优化将新建对象的发布重排序至 final 字段的写操作之前


#### 3.String，Stringbuffer，StringBuilder的区别
String: 线程不安全。 String a=""（常量池）和new String("")的空间开辟。
StringBuffer: 线程安全  s1+"c"是在堆内存创建地址？
StringBuilder:  线程不安全
 
 
#### 4.ArrayList和LinkedList有什么区别
ArrayList 数组 怎么变安全？
LinkedList 双向链表
CopyOnWriteArrayList  用volatile Object[] array写副本的方式及时更新数据给读线程但写保证单线程。在读多写少下适合ArrayList变得安全


#### 5.讲讲类的实例化顺序，比如父类静态数据，构造函数，字段，子类静态数据，构造函数，字 段，当new的时候，他们的执行顺序
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
HashMap


#### 7.JAVA8的ConcurrentHashMap为什么放弃了分段锁，有什么问题吗，如果你来设计，你如何 设计
ConcurrentHashMap


#### 8.有没有有顺序的Map实现类，如果有，他们是怎么保证有序的
TreeMap

#### 9.抽象类和接口的区别，类可以继承多个类么，接口可以继承多个接口么,类可以实现多个接口么
抽象类可以实现公共父类方法，而接口不能
类单继承多实现


#### 10.继承和聚合的区别在哪
类继承另外的一个类的功能
聚合体现的是整体与部分、拥有的关系


#### 11.IO模型有哪些，讲讲你理解的nio ，他和bio，aio的区别是啥，谈谈reactor模型
IO模型 
 1.blocking IO   
 2.non-blocking IO   
 3.IO multiplexing(事件驱动IO) select/epoll这个function会不断的轮询所负责的所有socket
 4.async IO
NIO理解 
零拷贝


#### 12.反射的原理，反射创建类实例的三种方式
原理  NativeMethodAccessorImpl/(new MethodAccessorGenerator()).generateMethod 15作为一个岭
不足
三种方式
1.class1 = Person.class;
2.class2 = p1.getClass();
3.class3 = Class.forName("cn.fang.Person");


#### 13.反射中Class.forName和ClassLoader.loadClass()区别 
class.forName()除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块，还会执行给静态变量赋值的静态方法
classLoader.loadClass() 只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块


#### 14.描述动态代理的几种实现方式，分别说出相应的优缺点
cglib：ASM  Enhancer 
jdk：反射机制(基于接口) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, new InvokerInvocationHandler(invoker));
javassist（最慢）：


#### 15.动态代理与cglib实现的区别


#### 16.为什么CGlib方式可以对接口实现代理


#### 17.final的用途
1.不可变的是变量的引用而非引用指向对象的内容
2.被final修饰的方法，JVM会尝试为之寻求内联这对于提升Java的效率是非常重要的。因此假如能确定方法不会被继承，那么尽量将方法定义为final的
3.被final修饰的常量，在编译阶段会存入调用类的常量池中


#### 18.写出三种单例模式实现
1.懒汉式  
2.饿汉式
3.Holder  private static class Holder {  private static final Singleton SINGLETON = new Singleton(); }
4.Double Check
5.枚举 《Effective Java》

#### 19.如何在父类中为子类自动完成所有的hashcode和equals实现？这么做有何优劣

在应用程序的执行期间，只要对象的equals方法的比较操作所用到的信息没有被修改，那么对这同一个对象调用多次，hashCode方法都必须始终如一地返回同一个整数。在同一个应用程序的多次执行过程中，每次执行所返回的整数可以不一致。
如果两个对象根据equals()方法比较是相等的，那么调用这两个对象中任意一个对象的hashCode方法都必须产生同样的整数结果。
如果两个对象根据equals()方法比较是不相等的，那么调用这两个对象中任意一个对象的hashCode方法，则不一定要产生相同的整数结果。
         但是程序员应该知道，给不相等的对象产生截然不同的整数结果，有可能提高散列表的性能。

#### 20.请结合OO设计理念，谈谈访问修饰符public、private、protected、default 在应用设计中的作用


#### 21.深拷贝和浅拷贝区别


#### 22.数组和链表数据结构描述，各自的时间复杂度


#### 23.error和exception的区别，CheckedException，RuntimeException的区别


#### 24.请列出5个运行时异常


#### 25.在自己的代码中，如果创建一个java.lang.String类，这个类是否可以被类加载器加载？为什么


#### 26.说一说你对java.lang.Object对象中hashCode和equals方法的理解。在什么场景下需要重新实现这两个方法


#### 27.在jdk1.5中，引入了泛型，泛型的存在是用来解决什么问题


#### 28.这样的a.hashcode() 有什么用，与a.equals(b)有什么关系


#### 29，有没有可能2个不相等的对象有相同的hashcode,


#### 30.Java中的HashSet内部是如何工作的
HashSet


#### 31.什么是序列化，怎么序列化，为什么序列化，反序列化会遇到什么问题，如何解决


#### 32.java8的新特性。


#### 33.volatile
为了减轻CPU加载代码压力 对没个线程缓存本地工作空间保存主存的副本
内存屏障  1.阻止屏障两边的指令重排序  2.强制把写缓冲区/高速缓存中的脏数据等写回主内存，让缓存中相应的数据失效
a.lfence，Load Barrier 读屏障
b.sfence, Store Barrier 写屏障
c.mfence, 是一种全能型的屏障具备ifence和sfence的能力
d.Lock前缀，Lock不是一种内存屏障但是它能完成类似内存屏障的功能。Lock会对CPU总线和高速缓存加锁可以理解为CPU指令级的一种锁

指令重排序  load addl $0x0, (%esp)这个命令相当于内存屏障
内存屏障禁止重排就是利用lock把lock前面的“整体”锁住，当前面的完成了之后lock后面“整体”的才能完成，当写完成后，释放锁，把缓存刷新到主内存。
final语义中的内存屏障
