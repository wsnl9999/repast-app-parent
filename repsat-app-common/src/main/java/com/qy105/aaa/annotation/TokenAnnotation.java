package com.qy105.aaa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/13 21:27
 * @description：
 * @modified By：
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenAnnotation {
}
