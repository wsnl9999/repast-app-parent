package com.qy105.aaa.controller;

import com.qy105.aaa.model.OmsCartItem;
import com.qy105.aaa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/21 0:01
 * @description：
 * @modified By：
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     * @Author      : ws
     * @Description : 创建订单表
     * @Params:     : [omsCartItem]
     * @Return      : java.lang.Boolean
     * @Date        : Created in 2020/3/22 19:53
     **/
    @PostMapping("createOrder")
    public Boolean createOrder(@RequestBody OmsCartItem omsCartItem, @RequestParam("addressId") Object addressId, @RequestParam("time") String time, @RequestParam("couponId") int couponId){
        return orderService.createOrder(omsCartItem,addressId,time,couponId);
    }
}
