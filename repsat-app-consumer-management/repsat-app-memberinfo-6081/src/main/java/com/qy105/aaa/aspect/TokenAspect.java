package com.qy105.aaa.aspect;

import com.qy105.aaa.service.IRepastService;
import javafx.beans.binding.ObjectExpression;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/13 21:29
 * @description：
 * @modified By：
 */
@Slf4j
@Component
@Aspect
public class TokenAspect {
    @Autowired
    private IRepastService iRepastService;

    @Pointcut("@annotation(com.qy105.aaa.annotation.TokenAnnotation)")
    public void pointcut(){

    }
    @Before("pointcut()")
    public Object before(){
        System.out.println("==========执行前置通知===============");
        Object res = new Object();
        Boolean aBoolean = iRepastService.tokenCheck();
        if(aBoolean){
            return res;
        }
        return null;
    }
}
