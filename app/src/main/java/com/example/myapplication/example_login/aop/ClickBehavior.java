package com.example.myapplication.example_login.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户行为统计
 */
@Target(ElementType.METHOD) // 目标作用再方法上
@Retention(RetentionPolicy.RUNTIME) // 生命周期
public @interface ClickBehavior {
    String value();


}
