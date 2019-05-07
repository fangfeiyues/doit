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
final


#### 3.String，Stringbuffer，StringBuilder的区别
String: 线程不安全。 String a=""（常量池）和new String("")的空间开辟。
StringBuffer: 线程安全  s1+"c"是在堆内存创建地址？
StringBuilder:  线程不安全
 
 
#### 4.ArrayList和LinkedList有什么区别
ArrayList 数组
LinkedList 双向链表

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

#### 7.JAVA8的ConcurrentHashMap为什么放弃了分段锁，有什么问题吗，如果你来设计，你如何 设计

#### 8.有没有有顺序的Map实现类，如果有，他们是怎么保证有序的
TreeMap

#### 9.抽象类和接口的区别，类可以继承多个类么，接口可以继承多个接口么,类可以实现多个接口么
抽象类可以实现公共父类方法，而接口不能
类单继承多实现


#### 10.继承和聚合的区别在哪
类继承另外的一个类的功能
聚合体现的是整体与部分、拥有的关系


#### 11.IO模型有哪些，讲讲你理解的nio ，他和bio，aio的区别是啥，谈谈reactor模型。
#### 12.反射的原理，反射创建类实例的三种方式是什么。
#### 13.反射中，Class.forName和ClassLoader区别 。
#### 14.描述动态代理的几种实现方式，分别说出相应的优缺点。
#### 15.动态代理与cglib实现的区别。
#### 16.为什么CGlib方式可以对接口实现代理。
#### 17.final的用途。
#### 18.写出三种单例模式实现 。
#### 19.如何在父类中为子类自动完成所有的hashcode和equals实现？这么做有何优劣。
#### 20.请结合OO设计理念，谈谈访问修饰符public、private、protected、default在应用设 计中的作用。