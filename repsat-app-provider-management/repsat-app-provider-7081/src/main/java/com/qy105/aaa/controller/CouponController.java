package com.qy105.aaa.controller;

import com.qy105.aaa.model.Coupon;
import com.qy105.aaa.model.Member;
import com.qy105.aaa.scheduledservice.ScheduledService;
import com.qy105.aaa.service.CouponService;
import com.qy105.aaa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/13 16:24
 * @description：
 * @modified By：
 */
@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;
    @Autowired
    private MemberService memberService;
    /**
     * create by: ws
     * description: TODO
     * 获取所有优惠券
     * create time: 16:26 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("getAllCoupon")
    public List<Coupon> getAllCoupon(){
        return couponService.getAllCoupon();
    }
    /**
     * create by: ws
     * description: TODO
     * 领取优惠券方法
     *
     * create time: 17:45 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("/addCoupon")
    public Integer addCoupon(@RequestBody Coupon coupon){
    /*coupon.setId(1L);*/
        Member member = memberService.getMember();
        return couponService.addCoupon(coupon,member);
    }
    /**
     * create by: ws
     * description: TODO
     * g根据用户openid查询当前用户拥有的优惠券
     * create time: 14:22 2020/3/14
     * * @Param: null
     * @return
     */
    @PostMapping("/selectCouponByOpenId")
    public List<Coupon> selectCouponByOpenId(){
        Member member = memberService.getMember();
        return couponService.selectCouponByOpenId(member);
    }
    @Autowired
    private ScheduledService scheduledService;
    @PostMapping("/Test")
    public void test(){
        scheduledService.checkCouponStatus();
    }
}
