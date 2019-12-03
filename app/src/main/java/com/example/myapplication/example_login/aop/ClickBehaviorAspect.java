package com.example.myapplication.example_login.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MemberSignature;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect // 定义一个切面类
public class ClickBehaviorAspect {

    private final static  String TAG = "ClickBehaviorAspect";

    // 1. 应用中用到了哪些注解，放到当前的切入点中进行处理（找到需要处理的切入点）
    // execution 语法, 以方法执行时作为切入点，出发 Aspect 类

    // 通配符 * *(..) 可以处理 ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.example.myapplication.example_login.aop.ClickBehavior * *(..))")
    public void methodPointCut() {

    }
    // 2. 处理切入点, 方法名定义 有规则：对应的方法名有规则，返回值 参数 异常 一一对应
    @Around("methodPointCut()")
    public Object joinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取签名方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // 获取方法所属类名
        String className= methodSignature.getDeclaringType().getSimpleName();

        // 获取方法名
        String methodName = methodSignature.getName();

        // 获取方法注解值
        String annotationName = methodSignature.getMethod().getAnnotation(ClickBehavior.class).value();

        // 统计方法执行时间
        long begin = System.currentTimeMillis();
        Log.d(TAG, "Click behavior start >>");

        // 执行方法
        Object result= joinPoint.proceed();

        long duration = System.currentTimeMillis() - begin;
        Log.d(TAG, "Click behavior end >> " + duration);

        Log.e(TAG, String.format("%s 功能： %s.%s(), duration: %d ms", annotationName, className, methodName, duration));
        return result;
    }
}
