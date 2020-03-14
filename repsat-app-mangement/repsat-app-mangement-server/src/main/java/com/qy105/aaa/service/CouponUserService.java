package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.CouponUserMapper;
import com.qy105.aaa.model.CouponUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/13 18:59
 * @description：
 * @modified By：
 */
@Service
public class CouponUserService extends BaseService<CouponUser> {
    @Autowired
    private CouponUserMapper couponUserMapper;
    @Override
    public Mapper<CouponUser> getMapper() {
        return couponUserMapper;
    }

    public Integer addCoupon(CouponUser couponUser){
        return getMapper().insert(couponUser);
    }
}
