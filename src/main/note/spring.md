<!--从最熟悉的地方开始如注解，实现类，继承接口等-->
--- 
### Spring
#### 1.web.xml基本配置
```
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:application.xml</param-value>
    </context-param>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
```
ContextLoaderListener 会监听到tomcat容器启动过程中触发容器初始化事件，触发 contextInitialized 方法

#### 2.IOC容器启动
以`ApplicationContext`的具体实现`FileSystemXmlApplicationContext` 为例
IoC 容器的初始化过程分为三步骤：
1.Resource 定位
2.BeanDefinition 的载入和解析，将用户定义的Bean表示成IoC内部数据结构BeanDefinition且维护着<beanName,BeanDefinition>
3.BeanDefinition 注册，这里可以通过 lazyinit = false 完成容器初始化状态（创建bean）

#### 3.Bean的加载
AbstractApplicationContext#getBean(name)  继承于BeanFactory 通过applicationContext拿到beanName
AbstractBeanFactory# <T> T doGetBean()
    1. beanName = transformedBeanName(name) 返回 bean 名称，剥离工厂引用前缀
    2. sharedInstance = getSingleton(beanName) 先从缓存中或者实例工厂中获取 Bean 对象 
        + 三级缓存实现解决set注入的循环依赖
          1. singletonObjects 获取为空且正在创建下 
          2. 从 earlySingletonObjects 获取为空且允许提前创建 
          3. 从 singletonFactories.get(beanName).getObject() 获取 不为空则放到earlySingletonObjects(二级缓存)。在createBean的填充属性之前就存放
    3. 不为空 bean = getObjectForBeanInstance(sharedInstance, name, beanName, null)
        + 缓存中拿取的是最原始的bean    
        + getObjectFromFactoryBean(FactoryBean<?>, beanName, boolean shouldPostProcess)
          1. isSingletonCurrentlyInCreation(beanName) 正在处理中不作处理
          2. beforeSingletonCreation(beanName)
          3. object = postProcessObjectFromFactoryBean(object, beanName) 执行processor.postProcessAfterInitialization()方法 
          4. afterSingletonCreation(beanName);
    3. 为空 isPrototypeCurrentlyInCreation  Spring 只解决单例模式下得循环依赖，在原型模式下如果存在循环依赖则会抛出异常
    4. getParentBeanFactory() 如果容器中没有找到，则从父类容器中加载
    5. 从容器中获取 beanName 相应的 GenericBeanDefinition 对象，并将其转换为 RootBeanDefinition 对象
    6. 处理所依赖的 dependsOn bean  如果一个 Bean 有依赖 Bean 的话，那么在初始化该 Bean 时是需要先初始化它所依赖的 Bean
    7. 单例isSingleton： 
        1.singletonObject = singletonFactory.getObject() 核心创建    
        2.addSingleton(beanName, singletonObject);
    8. 原型isPrototype：
        1. beforePrototypeCreation(beanName);
        2. prototypeInstance = createBean(beanName, mbd, args); 直接再次创建
        3. afterPrototypeCreation(beanName);
    9. 其他作用域getScope 
        核心流程和原型模式一样只不过获取 bean 实例是由 Scope#get(String name, ObjectFactory<?> objectFactory) 方法来实现

#### 4.创建Bean
0.创建spring容器
ApplicationContext
  继承 ListableBeanFactory 获取单个的bean
  继承 HierarchicalBeanFactory 设置父子继承BeanFactory关系
  AutowireCapableBeanFactory 在getAutowireCapableBeanFactory()方法
  ConfigurableListableBeanFactory 继承上面三个BeanFactory
附：

1.实例化bean createBeanInstance
AbstractAutowireCapableBeanFactory#doCreateBean(beanName, RootBeanDefinition, Object[] args)
- BeanWrapper = createBeanInstance(beanName, mbd, args); 实例化bean
    - Supplier<?> instanceSupplier = mbd.getInstanceSupplier();
    - if (mbd.getFactoryMethodName() != null)               // 如果存在工厂方法
        - instantiateUsingFactoryMethod(beanName, mbd, args)
    - mbd.resolvedConstructorOrFactoryMethod != null        // 是否解析
        - autowireNecessary = mbd.constructorArgumentsResolved;    // 是否构造注入
           - autowireConstructor()
        - instantiateBean(beanName, mbd)
    - Constructor<?>[] ctors = determineConstructorsFromBeanPostProcessors(beanClass, beanName)  // 获取构造函数

2.注入属性 populateBean

3.初始化 initializeBean(beanName, Object bean, RootBeanDefinition mbd)
Aware: BeanNameAware /BeanClassLoaderAware /BeanFactoryAware
BeanPostProcessor: 在 Bean 完成实例化后，如果我们需要对其进行一些配置、增加一些自己的处理逻辑如AOP

invokeAwareMethods()
wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
    - getBeanPostProcessors()
        - #registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory)
invokeInitMethods(beanName, wrappedBean, mbd)
wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)

#### 注解

@autowire，1.反射该bean依赖的beanName   2. BeanFactory.getName(beanName) 方法即可获取对应的依赖实例






```
public void refresh() throws BeansException, IllegalStateException {
	synchronized (this.startupShutdownMonitor) {
		// Prepare this context for refreshing.
		// 准备更新上下文，设置开始时间，标记活动标志，初始化配置文件中的占位符
		prepareRefresh();

		// Tell the subclass to refresh the internal bean factory.
		// 一、 web工程 AbstractRefreshableApplicationContext
		// 将 bean 定义加载到给定的 BeanFactory 中
		// 1. createBeanFactory(); 为此上下文创建内部 BeanFactory
		// 2. customizeBeanFactory(beanFactory); 定制 BeanFactory，是否允许 BeanDefinition 覆盖、是否允许循环引用
		// 3. loadBeanDefinitions(beanFactory); 通过 BeanDefinitionReader 解析 xml 文件，解析封装信息到 BeanDefinition，并将其 register 到 BeanFactory 中
		// 以 beanName为key将beanDefinition 存到 DefaultListableBeanFactory#beanDefinitionMap 中
		// 二、 SpringBoot GenericApplicationContext，实际 register 过程在 invokeBeanFactoryPostProcessors 中
		ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

		// Prepare the bean factory for use in this context.
		// 准备 BeanFactory 以便在此上下文中使用。
		// 1. 设置 BeanFactory 的类加载器
		// 2. 添加几个 BeanPostProcessor，
		// 3. 实例化几个特殊的 bean
		prepareBeanFactory(beanFactory);

		try {
			// Allows post-processing of the bean factory in context subclasses.
			// 在 AbstractApplicationContext#postProcessBeanFactory 为空实现，留给子类做扩展，不同 ApplicationContext 实现不同，不作详细描述
			postProcessBeanFactory(beanFactory);

			// Invoke factory processors registered as beans in the context.
			// Spring 的 SPI
			// 先调用 BeanDefinitionRegistryPostProcessor 和 ImportBeanDefinitionRegistrar 的实现类
			// 再调用 BeanFactoryPostProcessor 各个实现类的 postProcessBeanFactory(factory) 方法
			// 例如：ConfigurationClassPostProcessor 会扫描 <context:component-scan/> 和 @SpringBootApplication(scanBasePackages = "") 中的Component，并且将 @Configuration 类中的 @Bean register 到 BeanFactory 中
			// 扩展例如：MyBatis MapperScannerConfigurer 和 MapperScannerRegistrar，扫描Mapper register 到 BeanFactory 中
			invokeBeanFactoryPostProcessors(beanFactory);

			// 按照PriorityOrdered、Ordered、无序的顺序 在加入所有的beanPostProcessor给在初始化调用的时候使用
			// 这边 Bean 还没初始化，下面的 finishBeanFactoryInitialization 才是真正的初始化方法
			registerBeanPostProcessors(beanFactory);

			// Initialize message source for this context.
			// 初始化当前 ApplicationContext 的 MessageSource，解析消息的策略接口，用于支持消息的国际化和参数化
			// Spring 两个开箱即用的实现 ResourceBundleMessageSource 和 ReloadableResourceBundleMessageSource
			initMessageSource();

			// Initialize event multicaster for this context.
			// 初始化当前 ApplicationContext 的事件广播器
			initApplicationEventMulticaster();

			// Initialize other special beans in specific context subclasses.
			// 典型模板方法
			// 子类可以在实例化 bean 之前，做一些初始化工作，SpringBoot 会在这边启动 Web 服务
			onRefresh();

			// Check for listener beans and register them.
			// 向 initApplicationEventMulticaster() 初始化的 applicationEventMulticaster 注册事件监听器，就是实现 ApplicationListener 接口类
			// 观察者模式，例如实现了 ApplicationEvent，通过 ApplicationEventPublisher#publishEvent()，可以通知到各个 ApplicationListener#onApplicationEvent
			registerListeners();

			// Instantiate all remaining (non-lazy-init) singletons.
			// 初始化所有的 singletons bean（lazy-init 的除外）
			// Spring bean 初始化核心方法
			finishBeanFactoryInitialization(beanFactory);

			// Last step: publish corresponding event.
			// ApplicationEventPublisher#publishEvent() 初始化完成（ContextRefreshedEvent）事件
			finishRefresh();
		}

	}
}
```


