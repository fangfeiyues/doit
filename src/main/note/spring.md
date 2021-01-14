<!--������Ϥ�ĵط���ʼ��ע�⣬ʵ���࣬�̳нӿڵ�-->
--- 
### Spring
#### 1.web.xml��������
```
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:application.xml</param-value>
    </context-param>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
```
ContextLoaderListener �������tomcat�������������д���������ʼ���¼������� contextInitialized ����

#### 2.IOC��������
��`ApplicationContext`�ľ���ʵ��`FileSystemXmlApplicationContext` Ϊ��
IoC �����ĳ�ʼ�����̷�Ϊ�����裺
1.Resource ��λ
2.BeanDefinition ������ͽ��������û������Bean��ʾ��IoC�ڲ����ݽṹBeanDefinition��ά����<beanName,BeanDefinition>
3.BeanDefinition ע�ᣬ�������ͨ�� lazyinit = false ���������ʼ��״̬������bean��

#### 3.Bean�ļ���
AbstractApplicationContext#getBean(name)  �̳���BeanFactory ͨ��applicationContext�õ�beanName
AbstractBeanFactory# <T> T doGetBean()
    1. beanName = transformedBeanName(name) ���� bean ���ƣ����빤������ǰ׺
    2. sharedInstance = getSingleton(beanName) �ȴӻ����л���ʵ�������л�ȡ Bean ���� 
        + ��������ʵ�ֽ��setע���ѭ������
          1. singletonObjects ��ȡΪ�������ڴ����� 
          2. �� earlySingletonObjects ��ȡΪ����������ǰ���� 
          3. �� singletonFactories.get(beanName).getObject() ��ȡ ��Ϊ����ŵ�earlySingletonObjects(��������)����createBean���������֮ǰ�ʹ��
    3. ��Ϊ�� bean = getObjectForBeanInstance(sharedInstance, name, beanName, null)
        + ��������ȡ������ԭʼ��bean    
        + getObjectFromFactoryBean(FactoryBean<?>, beanName, boolean shouldPostProcess)
          1. isSingletonCurrentlyInCreation(beanName) ���ڴ����в�������
          2. beforeSingletonCreation(beanName)
          3. object = postProcessObjectFromFactoryBean(object, beanName) ִ��processor.postProcessAfterInitialization()���� 
          4. afterSingletonCreation(beanName);
    3. Ϊ�� isPrototypeCurrentlyInCreation  Spring ֻ�������ģʽ�µ�ѭ����������ԭ��ģʽ���������ѭ����������׳��쳣
    4. getParentBeanFactory() ���������û���ҵ�����Ӹ��������м���
    5. �������л�ȡ beanName ��Ӧ�� GenericBeanDefinition ���󣬲�����ת��Ϊ RootBeanDefinition ����
    6. ������������ dependsOn bean  ���һ�� Bean ������ Bean �Ļ�����ô�ڳ�ʼ���� Bean ʱ����Ҫ�ȳ�ʼ������������ Bean
    7. ����isSingleton�� 
        1.singletonObject = singletonFactory.getObject() ���Ĵ���    
        2.addSingleton(beanName, singletonObject);
    8. ԭ��isPrototype��
        1. beforePrototypeCreation(beanName);
        2. prototypeInstance = createBean(beanName, mbd, args); ֱ���ٴδ���
        3. afterPrototypeCreation(beanName);
    9. ����������getScope 
        �������̺�ԭ��ģʽһ��ֻ������ȡ bean ʵ������ Scope#get(String name, ObjectFactory<?> objectFactory) ������ʵ��

#### 4.����Bean
0.����spring����
ApplicationContext
  �̳� ListableBeanFactory ��ȡ������bean
  �̳� HierarchicalBeanFactory ���ø��Ӽ̳�BeanFactory��ϵ
  AutowireCapableBeanFactory ��getAutowireCapableBeanFactory()����
  ConfigurableListableBeanFactory �̳���������BeanFactory
����

1.ʵ����bean createBeanInstance
AbstractAutowireCapableBeanFactory#doCreateBean(beanName, RootBeanDefinition, Object[] args)
- BeanWrapper = createBeanInstance(beanName, mbd, args); ʵ����bean
    - Supplier<?> instanceSupplier = mbd.getInstanceSupplier();
    - if (mbd.getFactoryMethodName() != null)               // ������ڹ�������
        - instantiateUsingFactoryMethod(beanName, mbd, args)
    - mbd.resolvedConstructorOrFactoryMethod != null        // �Ƿ����
        - autowireNecessary = mbd.constructorArgumentsResolved;    // �Ƿ���ע��
           - autowireConstructor()
        - instantiateBean(beanName, mbd)
    - Constructor<?>[] ctors = determineConstructorsFromBeanPostProcessors(beanClass, beanName)  // ��ȡ���캯��

2.ע������ populateBean

3.��ʼ�� initializeBean(beanName, Object bean, RootBeanDefinition mbd)
Aware: BeanNameAware /BeanClassLoaderAware /BeanFactoryAware
BeanPostProcessor: �� Bean ���ʵ���������������Ҫ�������һЩ���á�����һЩ�Լ��Ĵ����߼���AOP

invokeAwareMethods()
wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
    - getBeanPostProcessors()
        - #registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory)
invokeInitMethods(beanName, wrappedBean, mbd)
wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)

#### ע��

@autowire��1.�����bean������beanName   2. BeanFactory.getName(beanName) �������ɻ�ȡ��Ӧ������ʵ��






```
public void refresh() throws BeansException, IllegalStateException {
	synchronized (this.startupShutdownMonitor) {
		// Prepare this context for refreshing.
		// ׼�����������ģ����ÿ�ʼʱ�䣬��ǻ��־����ʼ�������ļ��е�ռλ��
		prepareRefresh();

		// Tell the subclass to refresh the internal bean factory.
		// һ�� web���� AbstractRefreshableApplicationContext
		// �� bean ������ص������� BeanFactory ��
		// 1. createBeanFactory(); Ϊ�������Ĵ����ڲ� BeanFactory
		// 2. customizeBeanFactory(beanFactory); ���� BeanFactory���Ƿ����� BeanDefinition ���ǡ��Ƿ�����ѭ������
		// 3. loadBeanDefinitions(beanFactory); ͨ�� BeanDefinitionReader ���� xml �ļ���������װ��Ϣ�� BeanDefinition�������� register �� BeanFactory ��
		// �� beanNameΪkey��beanDefinition �浽 DefaultListableBeanFactory#beanDefinitionMap ��
		// ���� SpringBoot GenericApplicationContext��ʵ�� register ������ invokeBeanFactoryPostProcessors ��
		ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

		// Prepare the bean factory for use in this context.
		// ׼�� BeanFactory �Ա��ڴ���������ʹ�á�
		// 1. ���� BeanFactory ���������
		// 2. ��Ӽ��� BeanPostProcessor��
		// 3. ʵ������������� bean
		prepareBeanFactory(beanFactory);

		try {
			// Allows post-processing of the bean factory in context subclasses.
			// �� AbstractApplicationContext#postProcessBeanFactory Ϊ��ʵ�֣�������������չ����ͬ ApplicationContext ʵ�ֲ�ͬ��������ϸ����
			postProcessBeanFactory(beanFactory);

			// Invoke factory processors registered as beans in the context.
			// Spring �� SPI
			// �ȵ��� BeanDefinitionRegistryPostProcessor �� ImportBeanDefinitionRegistrar ��ʵ����
			// �ٵ��� BeanFactoryPostProcessor ����ʵ����� postProcessBeanFactory(factory) ����
			// ���磺ConfigurationClassPostProcessor ��ɨ�� <context:component-scan/> �� @SpringBootApplication(scanBasePackages = "") �е�Component�����ҽ� @Configuration ���е� @Bean register �� BeanFactory ��
			// ��չ���磺MyBatis MapperScannerConfigurer �� MapperScannerRegistrar��ɨ��Mapper register �� BeanFactory ��
			invokeBeanFactoryPostProcessors(beanFactory);

			// ����PriorityOrdered��Ordered�������˳�� �ڼ������е�beanPostProcessor���ڳ�ʼ�����õ�ʱ��ʹ��
			// ��� Bean ��û��ʼ��������� finishBeanFactoryInitialization ���������ĳ�ʼ������
			registerBeanPostProcessors(beanFactory);

			// Initialize message source for this context.
			// ��ʼ����ǰ ApplicationContext �� MessageSource��������Ϣ�Ĳ��Խӿڣ�����֧����Ϣ�Ĺ��ʻ��Ͳ�����
			// Spring �������伴�õ�ʵ�� ResourceBundleMessageSource �� ReloadableResourceBundleMessageSource
			initMessageSource();

			// Initialize event multicaster for this context.
			// ��ʼ����ǰ ApplicationContext ���¼��㲥��
			initApplicationEventMulticaster();

			// Initialize other special beans in specific context subclasses.
			// ����ģ�巽��
			// ���������ʵ���� bean ֮ǰ����һЩ��ʼ��������SpringBoot ����������� Web ����
			onRefresh();

			// Check for listener beans and register them.
			// �� initApplicationEventMulticaster() ��ʼ���� applicationEventMulticaster ע���¼�������������ʵ�� ApplicationListener �ӿ���
			// �۲���ģʽ������ʵ���� ApplicationEvent��ͨ�� ApplicationEventPublisher#publishEvent()������֪ͨ������ ApplicationListener#onApplicationEvent
			registerListeners();

			// Instantiate all remaining (non-lazy-init) singletons.
			// ��ʼ�����е� singletons bean��lazy-init �ĳ��⣩
			// Spring bean ��ʼ�����ķ���
			finishBeanFactoryInitialization(beanFactory);

			// Last step: publish corresponding event.
			// ApplicationEventPublisher#publishEvent() ��ʼ����ɣ�ContextRefreshedEvent���¼�
			finishRefresh();
		}

	}
}
```


