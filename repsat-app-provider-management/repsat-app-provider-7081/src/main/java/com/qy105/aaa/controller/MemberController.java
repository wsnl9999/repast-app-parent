package com.qy105.aaa.controller;

import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.model.LoginLog;
import com.qy105.aaa.model.Member;
import com.qy105.aaa.service.LoginLogService;
import com.qy105.aaa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.qy105.aaa.staticstatus.StaticCode.TOKEN;


/**
 * @author ：小男神
 * @date ：Created in 2020/3/11 17:48
 * @description：
 * @modified By：
 */
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private LoginLogService loginLogService;
    /**
     * create by: ws
     * description: TODO
     *          d登录方法
     * create time: 15:23 2020/3/12
     * * @Param: null
     * @return
     */
    @RequestMapping("/doLogin")
    public Boolean doLogin(@RequestBody Member member){
        return memberService.doLogin(member);
    }
    /**
     * create by: ws
     * description: TODO
     *          d退出登录方法
     * create time: 15:23 2020/3/12
     * * @Param: null
     * @return
     */
    @RequestMapping("/doLoginOut")
    public Boolean doLoginOut(@RequestParam(TOKEN) String token){
        return memberService.doLoginOut(token);
    }
    /**
     * 修改个人信息
     * @param member
     * @return
     */
    @PostMapping("/updateMember")
    public ResultData updateMember(@RequestBody Map member){
        return updateMember(member);

    }

    /**
     * 查询个人信息
     * @param member
     * @return
     */
    @PostMapping("/selectMember")
    public ResultData selcetMember(@RequestBody Map member){
        return selcetMember(member);
    }
    /**
     * create by: ws
     * description: TODO
     *          保存登录日志
     * create time: 15:23 2020/3/12
     * * @Param: null
     * @return
     */
    @PostMapping("/saveLog")
    public boolean saveLog(@RequestBody LoginLog loginLog){
        return loginLogService.addLoginLog(loginLog);
    }
    /**
     * create by: ws
     * description: TODO 查询用户积分操作
     * create time: 17:55 2020/3/14
     * * @Param: token
     * @return
     */
    @RequestMapping("/getIntegrationByToken")
    public List<Map> getIntegrationByToken(@RequestParam(TOKEN) String token) {
        return memberService.getIntegrationByToken(token);
    }

    @PostMapping("/tokenCheck")
    public Boolean tokenCheck(){
        return memberService.tokenCheck();
    }
}
