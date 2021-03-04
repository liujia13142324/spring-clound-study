package com.lj.springcloud.micoservice.aspect;

import com.lj.springcloud.annotation.OperationLogger;
import com.lj.springcloud.micoservice.service.LoggerService;
import com.lj.springcloud.session.SessionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class SysLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);
    @Autowired
    LoggerService loggerService;

    @Pointcut("@annotation(com.lj.springcloud.annotation.OperationLogger)")
    public void controllerAspect() {
        System.out.println("我是一个切入点");
    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("=====SysLogAspect前置通知开始=====");/*调用日志处理类去处理我们的日志*/
        handleLog(joinPoint, null);
    }

    @AfterReturning(pointcut = "controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("=====SysLogAspect后置通知开始=====");/*调用日志处理类去处理我们的日志*/
        handleLog(joinPoint, null);
    }

    @AfterThrowing(value = "controllerAspect()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        System.out.println("=====SysLogAspect异常通知开始=====");/*调用日志处理类去处理我们的日志，传入异常;*/
        handleLog(joinPoint, e);
    }

    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object proceed = null;
        System.out.println("=====SysLogAspect 环绕通知开始=====");/*调用日志处理类去处理我们的日志*/
        handleLog(joinPoint, null);
        try {
            proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    private void handleLog(JoinPoint joinPoint, Object o) {
        try {/*在这里我们首先根据得到的JoinPoint获得注解*/
            OperationLogger logger = giveController(joinPoint);
            /*判断一下返回的是不是空值，防止出现空指针错误*/
            if (logger == null) return;
            /* 获取目标方法的签名*/
            String signature = joinPoint.getSignature().toString();
            /*根据的带的签名去截取方法名*/
            /*在目标方法的签名当中以最后一个点加1开始，以包裹参数的第一个尖括号结尾截取方法名*/
            String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));/*当然这个变量并不会在这里用到*/
            String longTemp = joinPoint.getStaticPart().toLongString();/* 再通过得到调用方法的目标对象，从而获取它的类名*/
            /*再通过反射来得到这个类对象*/
            String classType = joinPoint.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);/*得到它内部所有的方法*/
            Method[] methods = clazz.getDeclaredMethods();
            System.out.println("methodName: " + methodName);
            /*对得到的方法集合进行遍历*/
            for (Method method : methods) {
                /*如果以这个方法上面的注解值日志注解并且方法的名称是之前截取到的方法名*/
                if (method.isAnnotationPresent(OperationLogger.class)
                        && method.getName().equals(methodName)) {
                    /* 这个时候就可以通过这个方法去获取其上面的注解 我们将获取主借注解的功能封装成一个方法，将之前反射得到的类对象当作参数传递过去*/
                    String clazzName = clazz.getName();
                    System.out.println("clazzName: " + clazzName + ", methodName: " + methodName);/* 调用方法，在这里实现的就是对方法的遍历*/
                    String process = SysLogAspect.process(clazz);
                    /* 这里就调用我们的服务层去将得到的数据写入数据库*/
                    loggerService.addLogger(process, methodName);
                }
            }
        } catch (Exception exp) {
            logger.error("异常信息:{}", exp);
            exp.printStackTrace();
        }
    }

    private OperationLogger giveController(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        /*将其强转为MethodSignature 在根据这个去得到具体使用的方法*/
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        /*根据方法提供的方法去的到它上面的注解，参数就是我们的自定义注解的类对象*//* 在这里，参数是什么样的注解，你得到的就是相应注解的对象，接收的时候返回值类型也就是相应的类型*/
        if (method != null) {
            method.isAnnotationPresent(OperationLogger.class);
            return method.getAnnotation(OperationLogger.class);
        }
        return null;
    }

    /*被调用的方法，在这里获得方法上面的注解返回即可，这里由于我们的注解比较的少，
    所以我们直接返回的就是字符串，如果有多个，可以分装一个对象这里直接返回对像即可*/
    public static String process(Class clazz) {/*找方法*/
        String modelName = null;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            OperationLogger annotation = method.getAnnotation(OperationLogger.class);
            /*获取指定类型的注解 动态代理对象，*/
            if (annotation != null) {/*说明有注解*/
                modelName = annotation.modelName();
                String user = SessionUtil.getCurrentUser();
                modelName = "用户："+user+" "+modelName ;
            }
        }
        return modelName;
    }
}
