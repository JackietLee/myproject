package com.jay.handsome.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/13 17:00
 */
@Aspect
@Component
public class MyAdvice {
    @Pointcut("@annotation(com.jay.handsome.advice.annotation.Log)")
    public void logPointCut() {}

    /**
     * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        System.out.println("begin "+beginTime);
        Object result = point.proceed();
        long time = System.currentTimeMillis();
        System.out.println("end "+time);
        System.out.println("time "+ (time-beginTime));
        return result;
    }

    @Before("logPointCut()")
    public void before() throws Throwable {
        System.out.println("before ");
    }

    @Pointcut("execution(* com.jay.handsome.service.MyService.myFunc())")
    private void eatCarrot(){}

    @Around("eatCarrot()")
    public void handlerRpcResult(ProceedingJoinPoint point) throws Throwable {
        System.out.println("前");
        //  原来的 TestServiceImpl.eatCarrot 逻辑，可视情况决定是否执行
        point.proceed();
        System.out.println("后");
    }
}
