package com.qy105.aaa.fallback;

import com.qy105.aaa.model.Coupon;
import com.qy105.aaa.model.LoginLog;
import com.qy105.aaa.model.Member;
import com.qy105.aaa.service.IRepastService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/11 17:36
 * @description：
 * @modified By：
 */
@Component
public class RepastFallBackFactory implements FallbackFactory<IRepastService> {
    @Override
    public IRepastService create(Throwable throwable) {
        IRepastService iRepastService = new IRepastService(){
            @Override
            public Boolean doLogin(Member member) {
                System.out.println("熔断登录方法");
                return null;
            }

            @Override
            public Boolean saveLog(LoginLog loginLog) {
                System.out.println("熔断日志方法");
                return null;
            }

            @Override
            public List<Coupon> getAllCoupon() {
                System.out.println("熔断查询优惠券方法");
                return null;
            }

            @Override
            public Integer addCoupon(Coupon coupon) {
                System.out.println("熔断领取优惠券方法");
                return null;
            }

            @Override
            public Boolean tokenCheck() {
                System.out.println("熔断检查方法");
                return null;
            }
        };
        return iRepastService;
    }
}
