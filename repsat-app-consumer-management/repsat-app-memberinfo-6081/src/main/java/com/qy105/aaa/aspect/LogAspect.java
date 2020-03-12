package com.qy105.aaa.aspect;

import com.qy105.aaa.annotation.LoginAnnotation;
import com.qy105.aaa.model.LoginLog;
import com.qy105.aaa.model.Member;
import com.qy105.aaa.service.IRepastService;
import com.qy105.aaa.util.AddressUtil;
import com.qy105.aaa.util.DateUtil;
import com.qy105.aaa.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import static com.qy105.aaa.staticstatus.StaticCode.*;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/12 14:19
 * @description：
 * @modified By：
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private IRepastService iRepastService;

    @Pointcut("@annotation(com.qy105.aaa.annotation.LoginAnnotation)")
    public void pointcut(){

    }
    /**
     * create by: ws
     * description: 定义环形切面
     *              ProceedingJoinPoint  封装了目标路径
     * create time: 14:27 2020/3/12
     * * @Param: null
     * @return
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws ClassNotFoundException, UnsupportedEncodingException {
        Object res = null;
        try{
            res = proceedingJoinPoint.proceed();
        }catch (Throwable t){
            t.printStackTrace();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddr = IPUtil.getIpAddr(request);
        //获取MemberController中doLogin方法中的参数
        Object[] args = proceedingJoinPoint.getArgs();
        Member member = new Member();
        member = (Member) args[0];
        //获取目标类的全限定名
        String name = proceedingJoinPoint.getTarget().getClass().getName();
        //获取切入的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        String opName = "";
        String opType = "";
        Class tagerClass = Class.forName(name);
        Method[] methods = tagerClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)){
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == args.length){
                    opName = method.getAnnotation(LoginAnnotation.class).operationName();
                    opType = method.getAnnotation(LoginAnnotation.class).operationType();
                    break;
                }
            }
        }
        //根据IP地址获取用户地址

            Map<String, Object> addrMap = AddressUtil.getAddresses(IPADDR, ENCODING);

        //获取当前时间
        String formatDate = DateUtil.formatDate(new Date(), FORMAT_DATE);
        LoginLog loginLog = new LoginLog();
        loginLog.setProvince((String) addrMap.get(PROVINCE));
        loginLog.setLoginType(3);
        loginLog.setCity((String) addrMap.get(CITY));
        loginLog.setCreateTime(formatDate);
        loginLog.setIp(ipAddr);
        loginLog.setOpenId(member.getOpenId());
        loginLog.setOperationType(opType);
        loginLog.setOperationName(opName);
        System.out.println("logAspect"+loginLog);
        boolean b = iRepastService.saveLog(loginLog);
        if(b){
            return res;//返回controller
        }
        return null;//直接结束
    }
}
