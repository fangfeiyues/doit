### SpringMVC

#### 1.SpringMVC����
```
<servlet>
    <servlet-name>spring</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring-servlet.xml</param-value> // Ĭ��
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>spring</servlet-name>
    <url-pattern>*.do</url-pattern>
</servlet-mapping>
```

����һ����Spring ContextLoaderListener����Ϊ��������Spring WebApplicationContext����(�����ж��)

#### 2.Servlet�����Ĺ�ϵ
HttpServletBean: ���� ServletConfig ���ɵ� Spring ��
FrameworkServlet: �����ʼ�� Spring Servlet WebApplicationContext ����
DispatcherServlet��onRefresh(ApplicationContext context)��ʼ���Ÿ����
1.MultipartResolver*  �ļ��ϴ�
2.LocaleResolver  ���ع��ʻ�
3.ThemeResolver  �������
4.HandlerMapping*  ������ƥ��ӿڣ���������( handler )�����Ĵ�����( handler )����������( HandlerInterceptor ���� )
5.HandlerAdapter*  �������������ӿ� ִ��ModelAndView handle(HttpServletRequest, HttpServletResponse, Object handler)����
6.HandlerExceptionResolver  �������쳣�������ӿ�
7.RequestToViewNameTranslator
8.ViewResolver*  ʵ��������ӿڣ�������ͼ���͹��ʻ���������յ���ͼ View ����
9.FlashMapManager

#### 3.DispatcherServlet# doGet()/doPost()
FrameworkServlet#processRequest(HttpServletRequest, HttpServletResponse).doService(request, response)
    doDispatch(request, response)  ִ������ַ�
        -processedRequest = checkMultipart(request);  �Ƿ��ϴ�����
        -1.HandlerExecutionChain mappedHandler = getHandler(processedRequest)   ���ص�HandlerExecutionChain= handler&HandlerInterceptors
           1.1 HandleMethod
        -2.HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler())    ��ȡHandlerAdapter
        -3.mappedHandler.applyPreHandle(processedRequest, response)             HandlerExecutionChainִ��������ǰ�÷���
        -4.mv = ha.handle(processedRequest, response, mappedHandler.getHandler())  ����handler()
        -5.mappedHandler.applyPostHandle(processedRequest, response, mv)        HandlerExecutionChainִ�������������÷���
        -6.processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException)  ����ִ�н��

HandlerMapping  ??HandlerMapping��ʱ��û���õ�handlerʲôʱ���ȡ��

#### 4. ���1 -- AbstractHandlerMapping ʵ����"��ȡ�����Ӧ�Ĵ���������������"�ĹǼ��߼�
  1. HandlerExecutionChain = DispatcherServlet #getHandler(HttpServletRequest)  ����HandlerExecutionChain��������ӹ����� ǰ/��ִ�й���������
  2. HandlerMethod = HandlerMapping #getHandler(request) -- AbstractHandlerMapping #getHandler(HttpServletRequest) -- AbstractHandlerMethodMapping #getHandlerInternal(HttpServletRequest)  
    handler == "public void com.fang.doit.frame.spring.service.DemoController.test()"
    1. AbstractUrlHandlerMapping
    2. AbstractHandlerMethodMapping   (@RequestMapping,@GetMapping,@PostMapping...)
        1. ע��� MappingRegistry
        2. �������� HandlerMethodMappingNamingStrategy<T> -- RequestMappingInfoHandlerMethodMappingNamingStrategy
  3. HandlerExecutionChain = getHandlerExecutionChain(handler, request);  ƥ��������
  
��� AbstractHandlerMapping
��ʼ����Mapping �� handler + method, �ͳɹ�ע�ᵽ mappingRegistry
����HandlerExecutionChain =  getHandler(HttpServletRequest request)
        
```xml   
   <!--MappedInterceptor (���˻���HandlerInterceptor,WebRequestInterceptor)-->
   <mvc:interceptors>       
      <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <mvc:exclude-mapping path="/login"/> ����
        <mvc:exclude-mapping path="/index"/>
        <bean class="package.interceptor.XXInterceptor"/>
      </mvc:interceptor>
   </mvc:interceptors>
```

#### ���2 -- HandlerInterceptor
    1.MappedInterceptor ֧�ֵ�ַƥ��
    2.HandlerInterceptorAdapter springBootƥ�� ���ᱻ AbstractHandlerMapping �� #detectMappedInterceptors(List<HandlerInterceptor> mappedInterceptors) ����ɨ�赽


#### ���3 AbstractHandlerMethodMapping
    HandlerMethodMappingNamingStrategy<T> namingStrategy   Handler �� Method �� Mapping ���������ɲ���
         @RequestMapping(name = "login", value = "user/login")
        1.mapping ���ַǿգ���ʹ�� mapping ������
        2.���nameΪ��USERCONTROLLER#login
    MappingRegistry mappingRegistry = new MappingRegistry()   Mappingע���
afterPropertiesSet() --> initHandlerMethods()
    --> processCandidateBean(String beanName)
        - isHandler(beanType)
    afterPropertiesSet() --> initHandlerMethods()
        --> processCandidateBean(String beanName)
            - isHandler(beanType)    Controller||RequestMapping
            - detectHandlerMethods(beanName)
                - registerHandlerMethod(handler, invocableMethod, mapping);  ע��hander��mapping
                    - register(T mapping, Object handler, Method method)   mapping={ /test}  handler = demoController method = test()



    HandlerMethod createHandlerMethod(Object handler, Method method)
        / handler="demoController",method="public void com.fang.doit.frame.spring.service.DemoController.test()"
