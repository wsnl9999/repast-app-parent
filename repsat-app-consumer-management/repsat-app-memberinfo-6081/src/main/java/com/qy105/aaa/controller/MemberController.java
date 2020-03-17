package com.qy105.aaa.controller;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/11 17:31
 * @description：
 * @modified By：
 */

import com.qy105.aaa.annotation.LoginAnnotation;
import com.qy105.aaa.base.BaseController;
import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.model.Member;
import com.qy105.aaa.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户信息",tags = "用户信息接口")
public class MemberController extends BaseController {
    @Autowired
    private IRepastService iRepastService;
    /**
     * create by: ws
     * description: TODO  登录方法
     * create time: 17:32 2020/3/11
     * * @Param: null
     * @return
     */
    @ApiOperation(value = "登录方法",tags = "用户执行登录操作")
    @RequestMapping("/doLogin")
    @LoginAnnotation(operationType = "登录",operationName = "普通用户操作")
    public ResultData doLogin(@RequestBody Member member){
        Boolean aBoolean = iRepastService.doLogin(member);
        if (aBoolean){
            return super.success();
        }
        return super.failed();
    }
    /**
     * create by: ws
     * description: TODO
     *          d退出登录方法
     * create time: 15:23 2020/3/12
     * * @Param: null
     * @return
     */
    @ApiOperation(value = "退出登录方法",tags = "用户执行退出登录操作")
    @RequestMapping("/doLoginOut")
    public ResultData doLoginOut(@RequestParam("token") String token){
        Boolean aBoolean = iRepastService.doLoginOut(token);
        if (aBoolean) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
    /**
     * create by: ws
     * description: TODO 查询用户积分操作
     * create time: 17:55 2020/3/14
     * * @Param: token
     * @return
     */
    @ApiOperation(value = "查询积分方法",tags = "查询用户积分")
    @RequestMapping("/getIntegrationByToken")
    public ResultData getIntegrationByToken(@RequestParam("token") String token) {
        List<Map> list = iRepastService.getIntegrationByToken(token);
        if (null != list) {
            return super.operationSuccess(list);
        }
        return super.operationFailed();
    }
}
