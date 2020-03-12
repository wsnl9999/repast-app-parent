package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.LoginLogMapper;
import com.qy105.aaa.model.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/12 15:23
 * @description：
 * @modified By：
 */
@Service
public class LoginLogService extends BaseService<LoginLog> {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public Mapper getMapper() {
        return loginLogMapper;
    }

    public Boolean addLoginLog(LoginLog loginLog){
        try {
            System.out.println("loginlogService"+loginLog);
            Integer insert = super.insert(loginLog);
            System.out.println("insert====="+insert);
            if (insert > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
