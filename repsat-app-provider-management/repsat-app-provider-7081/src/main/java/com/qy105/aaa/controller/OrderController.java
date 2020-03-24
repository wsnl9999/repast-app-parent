package com.qy105.aaa.controller;

import com.qy105.aaa.model.OmsCartItem;
import com.qy105.aaa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> d37db22dff825a5816df442def233d56ebd1a533

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
<<<<<<< HEAD

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
=======
    @PostMapping("createOrder")
    public Boolean createOrder(@RequestBody OmsCartItem omsCartItem, @RequestParam("addressId") Object addressId, @RequestParam("time") String time, @RequestParam("couponId") int couponId){
        return orderService.createOrder(omsCartItem,addressId,time,couponId);
>>>>>>> d37db22dff825a5816df442def233d56ebd1a533
    }
}
