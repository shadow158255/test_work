package com.example.my.product.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimerAspect {

    @Around("execution(* com.example.my.product.service.impl.*.*(..))")
    public Object timer(ProceedingJoinPoint pjp)throws Throwable{
        // 【需求】统计每个Service方法的耗时
        log.debug("在某个Service的某个方法之前执行了……");

        long start = System.currentTimeMillis();

        // 执行（处理）连接点，即执行业务方法
        // 注意：必须获取调用proceed()方法的返回值
        // 注意：如果连接点是Service中的方法，调用proceed()时的异常必须声明抛出，不可以try...catch
        Object result = pjp.proceed();

        long end = System.currentTimeMillis();
        log.debug("当前切面匹配到的组件类：{}", pjp.getTarget());
        log.debug("当前切面匹配到的方法：{}", pjp.getSignature());
        log.debug("当前切面匹配到的方法的参数列表：{}", pjp.getArgs());
        log.debug("执行耗时：{}毫秒", end - start);

        // 注意：必须返回调用proceed()方法的结果
        return result;
    }
}
