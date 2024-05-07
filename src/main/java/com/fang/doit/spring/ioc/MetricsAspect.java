//package com.fang.doit.spring.ioc;
//
//import org.apache.ibatis.binding.MapperMethod;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Array;
//import java.util.Map;
//import java.util.stream.Stream;
//
//import static java.util.stream.Collectors.toMap;
//
//
///**
// * @author by Feiyue on 2020/6/28 6:54 PM
// */
//
//@Aspect
//@Component
//@Slf4j
//public class MetricsAspect {
//    //��Spring������ע��ObjectMapper���Է���ͨ��JSON���л�����¼������κͳ���
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    //ʵ��һ������Java��������Ĭ��ֵ�Ĺ��ߡ���ʵ����Ҳ������һд�ܶ�if-else�ж����ͣ�Ȼ���ֶ�������Ĭ��ֵ������Ϊ�˼��ٴ���������һ��С���ɣ���ͨ����ʼ��һ������1��Ԫ�ص����飬Ȼ��ͨ����ȡ��������ֵ����ȡ��������Ĭ��ֵ
//    private static final Map<Class<?>, Object> DEFAULT_VALUES = Stream
//            .of(boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class)
//            .collect(toMap(clazz -> (Class<?>) clazz, clazz -> Array.get(Array.newInstance(clazz, 1), 0)));
//    public static <T> T getDefaultValue(Class<T> clazz) {
//        return (T) DEFAULT_VALUES.get(clazz);
//    }
//
//    //@annotationָʾ��ʵ�ֶԱ����Metricsע��ķ�������ƥ��
//    @Pointcut("within(@org.geekbang.time.commonmistakes.springpart1.aopmetrics.Metrics *)")
//    public void withMetricsAnnotation() {
//    }
//
//    //withinָʾ��ʵ����ƥ����Щ�����ϱ����@RestControllerע��ķ���
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
//    public void controllerBean() {
//    }
//
//    @Around("controllerBean() || withMetricsAnnotation())")
//    public Object metrics(ProceedingJoinPoint pjp) throws Throwable {
//        //ͨ�����ӵ��ȡ����ǩ���ͷ�����Metricsע�⣬�����ݷ���ǩ��������־��Ҫ����ķ�����������
//        MethodSignature signature = (MapperMethod.MethodSignature) pjp.getSignature();
//        Metrics metrics = signature.getMethod().getAnnotation(Metrics.class);
//
//        String name = String.format("��%s����%s��", signature.getDeclaringType().toString(), signature.toLongString());
//        //��Ϊ��ҪĬ�϶�����@RestController��ǵ�Web������ʵ��@Metricsע��Ĺ��ܣ�����������·����ϱ�Ȼ��û��@Metricsע��ģ�������Ҫ��ȡһ��Ĭ��ע�⡣��Ȼ�����ֶ�ʵ����һ��@Metricsע���ʵ����������Ϊ�˽�ʡ��������������ͨ����һ���ڲ����϶���@Metricsע�ⷽʽ��Ȼ��ͨ�������ȡע���С���ɣ������һ��Ĭ�ϵ�@Metricsע���ʵ��
//        if (metrics == null) {
//            @Metrics
//            final class c {}
//            metrics = c.class.getAnnotation(Metrics.class);
//        }
//        //���Դ����������ģ�����еĻ����������URL���Է��㶨λ����
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        if (requestAttributes != null) {
//            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//            if (request != null)
//                name += String.format("��%s��", request.getRequestURL().toString());
//        }
//        //ʵ�ֵ�����ε���־���
//        if (metrics.logParameters())
//            log.info(String.format("�������־������ %s �Ĳ����ǣ���%s��", name, objectMapper.writeValueAsString(pjp.getArgs())));
//        //ʵ�����ӵ㷽����ִ�У��Լ��ɹ�ʧ�ܵĴ�㣬�����쳣��ʱ�򻹻��¼��־
//        Object returnValue;
//        Instant start = Instant.now();
//        try {
//            returnValue = pjp.proceed();
//            if (metrics.recordSuccessMetrics())
//                //�������������У�����Ӧ����ʹ������Micrometer��ָ���ܣ��Ѵ����Ϣ��¼��ʱ���������ݿ��У�ʵ��ͨ��ͼ�����鿴�����ĵ��ô�����ִ��ʱ�䣬�����ƪ���ǻ��ص����
//                log.info(String.format("���ɹ���㡿���� %s �ɹ�����ʱ��%d ms", name, Duration.between(start, Instant.now()).toMillis()));
//        } catch (Exception ex) {
//            if (metrics.recordFailMetrics())
//                log.info(String.format("��ʧ�ܴ�㡿���� %s ʧ�ܣ���ʱ��%d ms", name, Duration.between(start, Instant.now()).toMillis()));
//            if (metrics.logException())
//                log.error(String.format("���쳣��־������ %s �����쳣��", name), ex);
//
//            //�����쳣��ʱ��ʹ��һ��ʼ�����getDefaultValue����������ȡ�������͵�Ĭ��ֵ
//            if (metrics.ignoreException())
//                returnValue = getDefaultValue(signature.getReturnType());
//            else
//                throw ex;
//        }
//        //ʵ���˷���ֵ����־���
//        if (metrics.logReturn())
//            log.info(String.format("��������־������ %s �ķ����ǣ���%s��", name, returnValue));
//        return returnValue;
//    }
//}
