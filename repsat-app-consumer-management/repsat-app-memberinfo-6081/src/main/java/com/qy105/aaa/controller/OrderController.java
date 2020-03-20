package com.qy105.aaa.controller;

import com.qy105.aaa.base.BaseController;
import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.model.OmsCartItem;
import com.qy105.aaa.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultData createOrder(OmsCartItem omsCartItem){
        Boolean order = iRepastService.createOrder(omsCartItem);
        if (order){
            return super.success();
        }
        return super.operationFailed();
    }
}
