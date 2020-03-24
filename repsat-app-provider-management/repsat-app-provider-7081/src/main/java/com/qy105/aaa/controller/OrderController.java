package com.qy105.aaa.controller;

import com.qy105.aaa.model.OmsCartItem;
import com.qy105.aaa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/createOrder")
    Boolean createOrder(@RequestBody OmsCartItem omsCartItem){
        return orderService.createOrder(omsCartItem);
    }
}
