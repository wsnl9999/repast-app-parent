package com.qy105.aaa.service;

import com.qy105.aaa.fallback.RepastFallBackFactory;
import com.qy105.aaa.model.LoginLog;
import com.qy105.aaa.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "memberinfo-interface",fallbackFactory = RepastFallBackFactory.class)
public interface IRepastService {
    /**
     * create by: ws
     * description: TODO   登录方法
     *
     * create time: 17:41 2020/3/11
     * * @Param: null
     * @return
     */
    @RequestMapping("/doLogin")
    Boolean doLogin(@RequestBody Member member);
    @RequestMapping("/doLoginOut")
    Boolean doLoginOut(@RequestParam("token") String token);
    @PostMapping("saveLog")
    Boolean saveLog(LoginLog loginLog);

}
