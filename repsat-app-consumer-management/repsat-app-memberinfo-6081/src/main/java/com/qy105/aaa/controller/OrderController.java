package com.qy105.aaa.controller;

import com.qy105.aaa.base.BaseController;
import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.model.OmsCartItem;
import com.qy105.aaa.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.qy105.aaa.staticstatus.StaticCode.ID;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/20 23:45
 * @description：
 * @modified By：
 */
@RestController
@Api(value = "订单",tags = "订单接口")
public class OrderController extends BaseController {
    @Autowired
    private IRepastService iRepastService;

    @ApiOperation(value = "提交订单方法",tags = "用户提交订单")
    @PostMapping("createOrder")
    public ResultData createOrder(OmsCartItem omsCartItem, @RequestParam("addressId") Object addressId, @RequestParam("time") String time, @RequestParam("couponId") int couponId){
        Boolean order = iRepastService.createOrder(omsCartItem,addressId,time,couponId);
        if (order){
            return super.success();
        }
        return super.operationFailed();
    }
    @ApiOperation(value = "删除订单方法",tags = "用户删除订单")
    @RequestMapping("/deleteOrder")
    public ResultData deleteOrder(@RequestParam(ID) Long id){
        Boolean order = iRepastService.deleteOrder(id);
        if (order) {
            return super.success();
        }
        return super.failed();
    }

}
