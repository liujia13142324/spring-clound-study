package com.lj.springcloud.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogger {
    String modelName() default "";
    //首先是被调用的方法的名称，其默认值是“”
    String option();
//之后就是这个用户所做的是什么操作

}
