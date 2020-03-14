package com.qy105.aaa.controller;

import com.qy105.aaa.annotation.TokenAnnotation;
import com.qy105.aaa.base.BaseController;
import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.model.Coupon;
import com.qy105.aaa.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/13 16:13
 * @description：
 * @modified By：
 */
@RestController
@Api(value = "优惠券信息",tags = "优惠券操作接口")
public class CouponController extends BaseController {

    @Autowired
    private IRepastService iRepastService;
    /**
     * create by: ws
     * description: TODO
     *      查询所有优惠券
     * create time: 16:16 2020/3/13
     * * @Param: null
     * @return
     */
    @TokenAnnotation
    @ApiOperation(value = "获取所有优惠券方法",tags = "获取所有优惠券")
    @RequestMapping("/getAllCoupon")
    public List<Coupon> getAllCoupon(){
        List<Coupon> allCoupon = iRepastService.getAllCoupon();
        System.out.println("============"+allCoupon);
        return  allCoupon;
    }
    @TokenAnnotation
    @ApiOperation(value = "领取优惠券方法",tags = "领取优惠券")
    @PostMapping("/addCoupon")
    public Integer addCoupon(Coupon coupon){
        System.out.println("con+++++++"+coupon);
        Integer integer = iRepastService.addCoupon(coupon);
        System.out.println(integer);
        return integer;

    }

}
