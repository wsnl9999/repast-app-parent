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



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.qy105.aaa.staticstatus.StaticCode.TOKEN;


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
        @PostMapping("/doLogin")
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
    @PostMapping("/doLoginOut")
    public ResultData doLoginOut(@RequestParam(TOKEN) String token){
        Boolean aBoolean = iRepastService.doLoginOut(token);
        if (aBoolean) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
    /**
     * 修改个人信息
     * @param member
     * @return
     */
    @PostMapping("/updateMember")
    public ResultData updateMember(Member member){
        ResultData resultData =iRepastService.updateMember(member);
        return resultData;
    }

    /**
     * 查询个人信息
     * @param member
     * @return
     */
    @PostMapping("/selectMember")
    public ResultData selcetMember(@RequestBody Member member){
        return iRepastService.selcetMember(member);
    }
    /**
     * create by: ws
     * description: TODO 查询用户积分操作
     * create time: 17:55 2020/3/14
     * * @Param: token
     * @return
     */
    @ApiOperation(value = "查询积分方法",tags = "查询用户积分")
    @PostMapping("/getIntegrationByToken")
    public ResultData getIntegrationByToken(@RequestParam(TOKEN) String token) {
        List<Map> list = iRepastService.getIntegrationByToken(token);
        if (null != list) {
            return super.operationSuccess(list);
        }
        return super.operationFailed();
    }
    /**
     * create by: ws
     * description: TODO 查询用户积分规则操作
     * create time: 17:55 2020/3/14
     * * @Param: token
     * @return
     */
    @ApiOperation(value = "查询积分规则方法",tags = "查询积分规则")
    @RequestMapping("/getMemberRuleSettingByToken")
    public ResultData getMemberRuleSettingByToken(@RequestParam(TOKEN) String token) {
        Map map = iRepastService.getMemberRuleSettingByToken(token);
        if (null != map) {
            return super.operationSuccess(map);
        }
        return super.operationFailed();
    }
}
