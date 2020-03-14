package com.qy105.aaa.mapper;

import com.qy105.aaa.model.Coupon;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CouponMapper extends Mapper<Coupon> {

    @Select("SELECT * from sms_coupon where id in (SELECT coupon_id from coupon_user where user_id = #{openId})")
    List<Coupon> selectCouponByOpenId(Long openId);

}