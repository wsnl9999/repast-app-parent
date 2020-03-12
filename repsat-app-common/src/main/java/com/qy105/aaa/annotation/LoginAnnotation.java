package com.qy105.aaa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/12 14:06
 * @description：
 * @modified By：
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginAnnotation {
    /**
     * create by: ws
     * description:
     *      用户执行的操作类型
     * create time: 14:13 2020/3/12
     * * @Param: null
     * @return
     */
    String operationType() default "";
    /**
     * create by: ws
     * description: TODO
     *      集体要执行的操作
     * create time: 14:14 2020/3/12
     * * @Param: null
     * @return
     */
    String operationName() default "";

}
