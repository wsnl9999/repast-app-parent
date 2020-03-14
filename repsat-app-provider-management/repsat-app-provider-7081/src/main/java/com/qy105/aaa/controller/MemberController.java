package com.qy105.aaa.controller;

import com.qy105.aaa.mapper.LoginLogMapper;
import com.qy105.aaa.model.LoginLog;
import com.qy105.aaa.model.Member;
import com.qy105.aaa.service.LoginLogService;
import com.qy105.aaa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Boolean doLoginOut(@RequestParam("token") String token){
        return memberService.doLoginOut(token);
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

    @PostMapping("/tokenCheck")
    public Boolean tokenCheck(){
        return memberService.tokenCheck();
    }
}
