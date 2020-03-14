package com.qy105.aaa.service;

import com.qy105.aaa.fallback.RepastFallBackFactory;
import com.qy105.aaa.model.Coupon;
import com.qy105.aaa.model.LoginLog;
import com.qy105.aaa.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "memberinfo-interface")
//,fallbackFactory = RepastFallBackFactory.class
public interface IRepastService {
    /**
     * create by: ws
     * description: TODO   登录方法
     *
     * create time: 17:41 2020/3/11
     * * @Param: null
     * @return
     */
    @RequestMapping("/doLogin")
    Boolean doLogin(@RequestBody Member member);
    /**
     * create by: ws
     * description: TODO
     *          添加登录日志方法
     * create time: 16:12 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("saveLog")
    Boolean saveLog(LoginLog loginLog);
    /**
     * create by: ws
     * description: TODO
     * 获取所有优惠券
     * create time: 16:45 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("getAllCoupon")
    List<Coupon> getAllCoupon();

    /**
     * create by: ws
     * description: TODO
     * 领取优惠券
     * create time: 16:47 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("/addCoupon")
    Integer addCoupon(@RequestBody Coupon coupon);
    /**
     * create by: ws
     * description: TODO
     * pa判断token是否为空
     * create time: 23:50 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("/tokenCheck")
    Boolean tokenCheck();
}
