package com.ninglz.aspectjLean.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author ninglz
 */
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.ninglz.aspectjLean.model.*.*(..))")
    public void log(){}

    @Before("log()")
    public void beforeLog(JoinPoint point){
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("[注解aspect]调用前方法为：" + methodName + ",参数为：" + args);
    }

    @Around("log()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[注解aspect]环绕前");
        Object result = joinPoint.proceed();
        System.out.println("[注解aspect]环绕后,调用方法: " + joinPoint.getSignature()+" 结束，返回结果:"+result);
        return result;
    }

    @AfterReturning(value = "log()",returning = "obj")
    public void afterReturning(JoinPoint point, Object obj) {
        String methodName = point.getSignature().getName();
        System.out.println("[注解aspect]调用后方法为：" + methodName+" ,返回:"+obj );
    }

    @AfterThrowing(value = "log()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[注解aspect]调用后方法后异常");
    }


    @After(value = "log()")
    public void afterLog(JoinPoint point){
        String methodName = point.getSignature().getName();
        System.out.println("[注解aspect]调用后方法为：" + methodName );
    }
}
