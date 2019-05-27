### 拆解Java虚拟机

##### 1.内存结构

|   区域     |       位置      |       存储      |    溢出场景      |  
|------------|-----------------|-----------------| -------------|
| 运行时常量池  |  JDK1.7以后由方法区移到Heap区  |  1.有类的版本，方法，字段，接口等描述信息 2.编译期生成的字面量和符号引用  |   大量String.intern()引用          |
| 方法区  |  JDK1.8后由在直接内存上的元空间代替  |  存储被虚拟机加载的类信息，常量，静态变量，即使编译后的代码   |    大量反射或动态代理生成的Class加载到方法区导致      |


#### 对象创建
1. 类加载检查
2. 分配内存
3. 初始化零值
4. 设置对象头
5. 执行init方法

#### 对象访问
1. 句柄
2. 直接指针

#### 垃圾收集器
1.Serial 



#### 内存分配策略


---

### 实际应用


##### 反射

>MethodAccessor有两个实现 
1. 委派实现  在本地实现/动态实现之间进行切换
2. 本地实现，当进入jvm之后我们就拥有每个Method实例所指向方法的地址。这时候反射调用无非就是把参数传进去

动态实现：动态生成字节码实现，运行效率比本地实现快20倍因为它无需Java到C++(Native)再到Java的切换，但生成字节码也是非常耗时所以单次来说还是本地块3-4倍
反射调用的开销：
`Class.forName()`会调用本地方法 Class.getMethods会遍历该类的全部共有方法 都很耗时可以考虑缓存
v1: 把128参数直接封装成Integer(128)  -- 1.8倍
v3: 设置好Object[]数组               -- 2.9倍  内联 - 逃逸分析成不逃逸 - 分配栈或虚拟空间 - 无法优化数组
v4: 取消委派直接动态实现 关闭检查目标方法权限  -- 1.3倍  
==> 1.变长参数方法导致的Object数组 2.基本类型的装箱拆箱 3.最重要的方法内联
方法内联：在编译一个方法时，将某个方法调用的目标方法也纳入编译范围内，并用其返回值替代原方法调用的过程

   59: aload_2                         // 加载 Method 对象
   60: aconst_null                     // 反射调用的第一个参数 null
   61: iconst_1
   62: anewarray Object                // 生成一个长度为 1 的 Object 数组
   65: dup
   66: iconst_0
   67: sipush 128
   70: invokestatic Integer.valueOf    // 将 128 自动装箱成 Integer
   73: aastore                         // 存入 Object 数组中
   74: invokevirtual Method.invoke     // 反射调用


【invokedynamic】
方法调用编译成 invokestatic, invokespecial, invokevirtual, invokeinterface  四种指令 --> 都需要目标方法
invokedynamic 调用点
public static void startRace(java.lang.Object)
       0: aload_0                // 加载一个任意对象
       1: invokedynamic race     // 调用赛跑方法

方法句柄：


【对象的内存布局】
// Foo foo = new Foo(); 编译而成的字节码
  0 new Foo
  3 dup
  4 invokespecial Foo()
  7 astore_1
  
压缩指针：




























