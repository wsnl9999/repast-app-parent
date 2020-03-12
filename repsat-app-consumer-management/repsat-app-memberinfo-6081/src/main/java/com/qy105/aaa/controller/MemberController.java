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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
    public ResultData doLogin(Member member){
        Boolean aBoolean = iRepastService.doLogin(member);
        if (aBoolean){
            return super.success();
        }
        return super.failed();
    }
}
