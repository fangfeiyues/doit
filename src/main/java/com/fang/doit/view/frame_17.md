#### 1. 讲讲tomcat结构，以及其类加载器流程，线程模型等。
***tomcat结构***

   1. tomcat主要是负责接收socket的Connector和处理Servlet的Container容器
   2. Connector主要有Endpoint, Processor, Adaptor组件负责接收数据流放入到线程池，解析socket为tomcat需要的request和response及适配成ServletRequest
   3. Container 通过Engine, Host, Context, Wrapper(Servlet)串联起来找到Url相应的Servlet
   
***启动流程***

   1. startup.sh 启动JVM运行Tomcat启动类Bootstrap
   2. Bootstrap 初始化Tomcat的类加载器 并创建 Catalina
   3. Catalina 解析Server.xml 创建相应的组件
   4. Server 管理Service组件 调用Service方法
   5. Service 管理连接器和顶层容器 Engine
   6. 怎么启动Servlet服务的???

***类加载器***

   Bootstrap ClassLoader 
            |
            |
   Extension ClassLoader
            |
            |
   Application ClassLoader
            |
            |
     Common ClassLoader
            |
            |
Cataline_CL    Shared_CL
                   |
                   |
                WebApp CL
                   |

***线程模型***

```
    <!--<Connector port="8080" protocol="HTTP/1.1"connectionTimeout="20000"redirectPort="8443" />-->
    <Connector port="8080" protocol="org.apache.coyote.http11.Http11NioProtocol"connectionTimeout="20000"redirectPort="8443" />
```
 1. BIO模式 Tomcat7默认方式。每个请求都要创建一个线程处理 开销大
 2. NIO模式 基于缓存并能提供非阻塞I/O操作的API
 3. APR模式 异步IO
    
#### 2. tomcat调优参数

并发控制参数(https://blog.csdn.net/qq_16681169/article/details/75003640)
    acceptCount   连接在被ServerSocketChannel accept之前就暂存在这个队列中
    acceptorThreadCount   Acceptor线程只负责从上述队列中取出已经建立连接的请求 参数acceptorThreadCount使用的Acceptor线程的个数
    maxConnections   这里就是tomcat对于连接数的一个控制，即最大连接数限制。一旦发现当前连接数已经超过了一定的数量（NIO默认是10000），上述的Acceptor线程就被阻塞了
    maxThreads  这个简单理解就算是上述worker的线程数。他们专门用于处理IO事件，默认是200

#### 3. Spring加载流程


#### BeanFactory和ApplicationContext区别
1. BeanFactory 是Spring最底层的接口
2. ApplicationContext是 BeanFactory的派生具有更完整的框架。
    继承MessageSource支持国际化；统一的资源文件访问方式；提供在监听器中注册Bean的事件；同时加载多个配置文件
3. BeanFactroy采用的是延迟加载形式来注入Bean的，即只有在使用到某个Bean时(调用getBean())，才对该Bean进行加载实例化。这样，我们就不能发现一些存在的Spring的配置问题
    如果Bean的某一个属性没有注入，BeanFacotry加载后，直至第一次使用调用getBean方法才会抛出异常。
    ②ApplicationContext，它是在容器启动时，一次性创建了所有的Bean。这样，在容器启动时，我们就可以发现Spring中存在的配置错误，这样有利于检查所依赖属性是否注入。 
    ApplicationContext启动后预载入所有的单实例Bean，通过预载入单实例bean ,确保当你需要的时候，你就不用等待，因为它们已经创建好了。
    ③相对于基本的BeanFactory，ApplicationContext 唯一的不足是占用内存空间。当应用程序配置Bean较多时，程序启动较慢
4. BeanFactory通常以编程的方式被创建，ApplicationContext还能以声明的方式创建，如使用ContextLoader。
5. BeanFactory和ApplicationContext都支持BeanPostProcessor、BeanFactoryPostProcessor的使用，但两者之间的区别是：BeanFactory需要手动注册，而ApplicationContext则是自动注册。


#### 4. Spring AOP的实现原理



#### 5. 讲讲Spring事务的传播属性
propagation = 
  REQUIRED       如果有事务, 那么加入事务, 没有的话新建一个(默认情况下)
  REQUIRES_NEW   不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
  SUPPORTS       支持当前事务，如果当前没有事务，就以非事务方式执行
  NOT_SUPPORTED  容器不为这个方法开启事务如果当前存在事务，就把当前事务挂起
  MANDATORY      必须在一个已有的事务中执行,否则抛出异常 mandatory
  NEVER          必须在一个没有的事务中执行,否则抛出异常 


#### 6. Spring如何管理事务的
编程式事务管理：将事务管理代码嵌入到业务方法中来控制事务的提交和回滚，在编程式事务中，必须在每个业务操作中包含额外的事务管理代码。(TransactionTemplate)
声明式事务管理：大多数情况下比编程式事务管理更好用。它将事务管理代码从业务方法中分离出来，以声明的方式来实现事务管理。事务管理作为一种横切关注点，可以通过AOP方法模块化。Spring通过Spring AOP框架支持声明式事务管理。@transaction或xml配置


#### 7. Spring怎么配置事务（具体说出一些关键的xml 元素
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
  

#### 8. 说说你对Spring的理解，非单例注入的原理；它的生命周期；循环注入的原理
非单例注入
    1.放弃控制反转通过ApplicationContextAware.getBean()向容器请求一个新的bean；
    2.Lookup方法注入??? 利用了容器的覆盖受容器管理的bean方法的能力  
         <bean><lookup-method name="createCommand" bean="asyncCommand"/></bean>    
    3.
    
生命周期 AbstractAutowireCapableBeanFactory#doCreateBean( beanName, RootBeanDefinition, args)
    Servlet生命周期：实例化，初始init，接受请求service，销毁destroy
    1. 实例化Bean instanceWrapper = createBeanInstance(beanName, mbd, args);
    2. 设置对象属性依赖注入 populateBean(beanName, mbd, instanceWrapper);
    3. 处理Aware接口 initializeBean#invokeAwareMethods(beanName, bean);
    4. BeanPostProcessor  applyBeanPostProcessorsBeforeInitialization&applyBeanPostProcessorsAfterInitialization
    5. 初始化Bean  ((InitializingBean) bean).afterPropertiesSet() & invokeCustomInitMethod
    6. DisposableBean 当Bean不再需要时，会经过清理阶段，如果Bean实现了DisposableBean这个接口，会调用其实现的destroy()方法
    7. destroy-method 如果这个Bean的Spring配置中配置了destroy-method属性，会自动调用其配置的销毁方法
    
循环注入：
    1. 构造器循环依赖   
         Spring容器将每一个正在创建的Bean 标识符放在一个"当前创建Bean池" 中，Bean标识符在创建过程中将一直保持在这个池中，
         因此如果在创建Bean过程中发现自己已经在"当前创建Bean池"里时将抛出BeanCurrentlyInCreationException异常表示循环依赖                 
    2. setter方法循环注入 
        2.1 setter方法注入 单例模式(scope=singleton) 
            进行注入"A"时由于提前暴露了"ObjectFactory"工厂从而使用它返回提前暴露一个创建中的Bean，在 singletonFactory 三级缓存中
        2.2 setter方法注入 非单例模式
    3.allowCircularReferences = false 不支持循环依赖


#### 9. springmvc用到的注解，作用及原理

@Controller

@RequestMapping AbstractHandlerMethodMapping 

@RequestParam / @RequestBody

@ResponseBody

#### springmvc 组件

1. MultipartResolver
2. LocaleResolver
3. ThemeResolver
4. HandlerMapping
    1. AbstractHandlerMapping 
    2. HandlerInterceptor
    3. AbstractHandlerMethodMapping
    4. AbstractUrlHandlerMapping
5. HandlerAdapter
    1. HandlerAdapter
    2. ServletInvocableHandlerMethod
    3. HandlerMethodArgumentResolver
    4. HandlerMethodReturnValueHandler
    5. HttpMessageConverter
6. HandlerExceptionResolver
7. RequestToViewNameTranslator
8. ViewResolver
9. FlashMapManager


#### 10. Springmvc中 DispatcherServlet 初始化过程
DispatcherServlet作为一个Servlet 
1. HttpServletBean#init()
2. FrameworkServlet#initServletBean()
3. FrameworkServlet#initWebApplicationContext()  创建上下文 ConfigurableWebApplicationContext
4. DispatcherServlet#onRefresh(ApplicationContext) #initStrategies(ApplicationContext context) 初始化九大组件

handlerMapping # Object strategy = createDefaultStrategy(context, clazz);
  
handlerAdapter

#### 11. netty的线程模型，netty如何基于reactor模型上实现的。

#### 12. 为什么选择netty。

#### 13. 什么是TCP粘包，拆包。解决方式是什么。

#### 14. netty的fashwheeltimer的用法，实现原理，是否出现过调用不够准时，怎么解决。

#### 15.netty的心跳处理在弱网下怎么办。

#### 16.netty的通讯协议是什么样的。

#### netty的poll,select,epoll
select：阻塞但一旦事件进入还是无差别轮询全部流
epoll:  event poll，不同于忙轮询和无差别轮询，epoll之会把哪个流发生了怎样的I/O事件通知我们。时间复杂度降为O(1)
详见Netty笔记

#### 17.springboot启动机制。