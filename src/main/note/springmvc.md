### SpringMVC

#### 1.SpringMVC配置
```
<servlet>
    <servlet-name>spring</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring-servlet.xml</param-value> // 默认
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>spring</servlet-name>
    <url-pattern>*.do</url-pattern>
</servlet-mapping>
```

配置一个以Spring ContextLoaderListener容器为父容器的Spring WebApplicationContext容器(可以有多个)

#### 2.Servlet容器的关系
HttpServletBean: 负责将 ServletConfig 集成到 Spring 中
FrameworkServlet: 负责初始化 Spring Servlet WebApplicationContext 容器
DispatcherServlet：onRefresh(ApplicationContext context)初始化九个组件
1.MultipartResolver*  文件上传
2.LocaleResolver  本地国际化
3.ThemeResolver  主题解析
4.HandlerMapping*  处理器匹配接口，根据请求( handler )获得其的处理器( handler )和拦截器们( HandlerInterceptor 数组 )
5.HandlerAdapter*  处理器适配器接口 执行ModelAndView handle(HttpServletRequest, HttpServletResponse, Object handler)方法
6.HandlerExceptionResolver  处理器异常解析器接口
7.RequestToViewNameTranslator
8.ViewResolver*  实体解析器接口，根据视图名和国际化，获得最终的视图 View 对象
9.FlashMapManager

#### 3.DispatcherServlet# doGet()/doPost()
FrameworkServlet#processRequest(HttpServletRequest, HttpServletResponse).doService(request, response)
    doDispatch(request, response)  执行请求分发
        -processedRequest = checkMultipart(request);  是否上传请求
        -1.HandlerExecutionChain mappedHandler = getHandler(processedRequest)   返回的HandlerExecutionChain= handler&HandlerInterceptors
           1.1 HandleMethod
        -2.HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler())    获取HandlerAdapter
        -3.mappedHandler.applyPreHandle(processedRequest, response)             HandlerExecutionChain执行拦截器前置方法
        -4.mv = ha.handle(processedRequest, response, mappedHandler.getHandler())  处理handler()
        -5.mappedHandler.applyPostHandle(processedRequest, response, mv)        HandlerExecutionChain执行拦截器后置置方法
        -6.processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException)  处理执行结果

HandlerMapping  ??HandlerMapping的时候没有拿到handler什么时候获取的

#### 4. 组件1 -- AbstractHandlerMapping 实现了"获取请求对应的处理器和拦截器们"的骨架逻辑
  1. HandlerExecutionChain = DispatcherServlet #getHandler(HttpServletRequest)  其中HandlerExecutionChain作用是添加过滤器 前/后执行过滤器方法
  2. HandlerMethod = HandlerMapping #getHandler(request) -- AbstractHandlerMapping #getHandler(HttpServletRequest) -- AbstractHandlerMethodMapping #getHandlerInternal(HttpServletRequest)  
    handler == "public void com.fang.doit.frame.spring.service.DemoController.test()"
    1. AbstractUrlHandlerMapping
    2. AbstractHandlerMethodMapping   (@RequestMapping,@GetMapping,@PostMapping...)
        1. 注册表 MappingRegistry
        2. 命名策略 HandlerMethodMappingNamingStrategy<T> -- RequestMappingInfoHandlerMethodMappingNamingStrategy
  3. HandlerExecutionChain = getHandlerExecutionChain(handler, request);  匹配拦截器
  
详解 AbstractHandlerMapping
初始化：Mapping 和 handler + method, 就成功注册到 mappingRegistry
请求：HandlerExecutionChain =  getHandler(HttpServletRequest request)
        
```xml   
   <!--MappedInterceptor (除此还有HandlerInterceptor,WebRequestInterceptor)-->
   <mvc:interceptors>       
      <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <mvc:exclude-mapping path="/login"/> 　　
        <mvc:exclude-mapping path="/index"/>
        <bean class="package.interceptor.XXInterceptor"/>
      </mvc:interceptor>
   </mvc:interceptors>
```

#### 组件2 -- HandlerInterceptor
    1.MappedInterceptor 支持地址匹配
    2.HandlerInterceptorAdapter springBoot匹配 不会被 AbstractHandlerMapping 的 #detectMappedInterceptors(List<HandlerInterceptor> mappedInterceptors) 方法扫描到


#### 组件3 AbstractHandlerMethodMapping
    HandlerMethodMappingNamingStrategy<T> namingStrategy   Handler 的 Method 的 Mapping 的名字生成策略
         @RequestMapping(name = "login", value = "user/login")
        1.mapping 名字非空，则使用 mapping 的名字
        2.如果name为空USERCONTROLLER#login
    MappingRegistry mappingRegistry = new MappingRegistry()   Mapping注册表
afterPropertiesSet() --> initHandlerMethods()
    --> processCandidateBean(String beanName)
        - isHandler(beanType)
    afterPropertiesSet() --> initHandlerMethods()
        --> processCandidateBean(String beanName)
            - isHandler(beanType)    Controller||RequestMapping
            - detectHandlerMethods(beanName)
                - registerHandlerMethod(handler, invocableMethod, mapping);  注册hander到mapping
                    - register(T mapping, Object handler, Method method)   mapping={ /test}  handler = demoController method = test()



    HandlerMethod createHandlerMethod(Object handler, Method method)
        / handler="demoController",method="public void com.fang.doit.frame.spring.service.DemoController.test()"
