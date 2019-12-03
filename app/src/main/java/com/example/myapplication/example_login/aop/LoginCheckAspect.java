package com.example.myapplication.example_login.aop;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.myapplication.example_login.ui.login.LoginActivity;
import com.example.myapplication.example_login.util.SharedPreferencesUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 通过 @Aspect 注解表明此类为一个切面
 */
@Aspect
public class LoginCheckAspect {
    /**
     * 定义切入点，通过 execution 语法定义哪些方法为切入点
     */
    @Pointcut("execution(@com.example.myapplication.example_login.aop.LoginCheck * *(..))")
    public void methondPointCut() {
    }


    @Around("methondPointCut()")
    public Object joinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context = (Context) joinPoint.getThis();

        String loginedUser = SharedPreferencesUtil.getLoginedUser();
        // 如果记录用户名不为空，则说明已登录
        boolean isLogin = !TextUtils.isEmpty(loginedUser);
        // 判断是否登录
        if (!isLogin) {
            // 未登录直接跳转到登录页面
            Toast.makeText(context, "请先登录！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return null;
        }

        // 如果登录了，则执行原方法
        return joinPoint.proceed();
    }
}
