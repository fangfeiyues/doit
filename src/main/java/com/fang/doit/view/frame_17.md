#### 1. 讲讲tomcat结构，以及其类加载器流程，线程模型等。
 > tomcat结构
   
 > 类加载器
 
 > 线程模型


#### 2. tomcat如何调优，涉及哪些参数。


#### 3. 讲讲Spring加载流程。

#### 4. Spring AOP的实现原理。

#### 5. 讲讲Spring事务的传播属性。
propagation = 
  REQUIRED       如果有事务, 那么加入事务, 没有的话新建一个(默认情况下)
  REQUIRES_NEW   不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
  SUPPORTS       支持当前事务，如果当前没有事务，就以非事务方式执行
  NOT_SUPPORTED  容器不为这个方法开启事务如果当前存在事务，就把当前事务挂起
  MANDATORY      必须在一个已有的事务中执行,否则抛出异常 mandatory
  NEVER          必须在一个没有的事务中执行,否则抛出异常 


#### 6. Spring如何管理事务的。
编程式事务管理：将事务管理代码嵌入到业务方法中来控制事务的提交和回滚，在编程式事务中，必须在每个业务操作中包含额外的事务管理代码。(TransactionTemplate)
声明式事务管理：大多数情况下比编程式事务管理更好用。它将事务管理代码从业务方法中分离出来，以声明的方式来实现事务管理。事务管理作为一种横切关注点，可以通过AOP方法模块化。Spring通过Spring AOP框架支持声明式事务管理。@transaction或xml配置


#### 7. Spring怎么配置事务（具体说出一些关键的xml 元素）。
1.@EnableTransactionManagement
2.
  <!-- 1 事务管理器 -->
      <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
          <property name="dataSource" ref="dataSource"></property>
       </bean>
  <!-- 2 事务详情（事务通知）, 在aop筛选基础上，比如对ABC三个确定使用什么样的事务。例如：AC读写、B只读 等 -->
      <tx:advice id="txAdvice" transaction-manager="txManager">
          <tx:attributes>
              <tx:method name="transfer" propagation="REQUIRED" isolation="DEFAULT"/>
          </tx:attributes>
      </tx:advice>
  <!-- 3 AOP编程，利用切入点表达式从目标类方法中 确定增强的连接器，从而获得切入点 -->
      <aop:config>
          <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.ys.service..*.*(..))"/>
      </aop:config>
  

#### 8. 说说你对Spring的理解，非单例注入的原理？它的生命周期？循环注入的原理，aop的实现原理，说说aop中的几个术语，它们是怎么相互工作的。
非单例注入
    1.放弃控制反转通过ApplicationContextAware.getBean()向容器请求一个新的bean；
    2.Lookup方法注入??? 利用了容器的覆盖受容器管理的bean方法的能力  <bean><lookup-method name="createCommand" bean="asyncCommand"/></bean>    
    3.
生命周期：
    1、实例化一个Bean－－也就是我们常说的new；

    2、按照Spring上下文对实例化的Bean进行配置－－也就是IOC注入；

    3、如果这个Bean已经实现了BeanNameAware接口，会调用它实现的setBeanName(String)方法，此处传递的就是Spring配置文件中Bean的id值

    4、如果这个Bean已经实现了BeanFactoryAware接口，会调用它实现的setBeanFactory(setBeanFactory(BeanFactory)传递的是Spring工厂自身（可以用这个方式来获取其它Bean，只需在Spring配置文件中配置一个普通的Bean就可以）；

    5、如果这个Bean已经实现了ApplicationContextAware接口，会调用setApplicationContext(ApplicationContext)方法，传入Spring上下文（同样这个方式也可以实现步骤4的内容，但比4更好，因为ApplicationContext是BeanFactory的子接口，有更多的实现方法）；

    6、如果这个Bean关联了BeanPostProcessor接口，将会调用postProcessBeforeInitialization(Object obj, String s)方法，BeanPostProcessor经常被用作是Bean内容的更改，并且由于这个是在Bean初始化结束时调用那个的方法，也可以被应用于内存或缓存技术；

    7、如果Bean在Spring配置文件中配置了init-method属性会自动调用其配置的初始化方法。

    8、如果这个Bean关联了BeanPostProcessor接口，将会调用postProcessAfterInitialization(Object obj, String s)方法、；

    注：以上工作完成以后就可以应用这个Bean了，那这个Bean是一个Singleton的，所以一般情况下我们调用同一个id的Bean会是在内容地址相同的实例，当然在Spring配置文件中也可以配置非Singleton，这里我们不做赘述。

    9、当Bean不再需要时，会经过清理阶段，如果Bean实现了DisposableBean这个接口，会调用那个其实现的destroy()方法；

    10、最后，如果这个Bean的Spring配置中配置了destroy-method属性，会自动调用其配置的销毁方法。
    
循环注入：
    1. 构造器循环依赖   
         Spring容器将每一个正在创建的Bean 标识符放在一个“当前创建Bean池”中，Bean标识符在创建过程中将一直保持在这个池中，
         因此如果在创建Bean过程中发现自己已经在“当前创建Bean池”里时将抛出BeanCurrentlyInCreationException异常表示循环依赖                 
    2. setter方法循环注入 
        2.1 setter方法注入 单例模式(scope=singleton) 
            进行注入“A”时由于提前暴露了“ObjectFactory”工厂从而使用它返回提前暴露一个创建中的Bean，在 singletonFactory 三级缓存中
        2.2 setter方法注入 非单例模式
    3.allowCircularReferences = false 不支持循环依赖

aop原理：

#### 16.springmvc用到的注解，作用是什么，原理。

#### 9. Springmvc中 DispatcherServlet 初始化过程。

#### 10. netty的线程模型，netty如何基于reactor模型上实现的。

#### 11. 为什么选择netty。

#### 12. 什么是TCP粘包，拆包。解决方式是什么。

#### 13. netty的fashwheeltimer的用法，实现原理，是否出现过调用不够准时，怎么解决。

#### 14.netty的心跳处理在弱网下怎么办。

#### 15.netty的通讯协议是什么样的。

#### netty的poll,select,epoll
select：阻塞但一旦事件进入还是无差别轮询全部流
epoll:  event poll，不同于忙轮询和无差别轮询，epoll之会把哪个流发生了怎样的I/O事件通知我们。时间复杂度降为O(1)
详见Netty笔记

#### 17.springboot启动机制。