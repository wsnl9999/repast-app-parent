package com.qy105.aaa.mapper;

import com.qy105.aaa.model.Coupon;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CouponMapper extends Mapper<Coupon> {
    @Select("SELECT * FROM sms_coupon where id in (SELECT coupon_id FROM coupon_user WHERE user_id = #{key})")
    List<Coupon> selectCouponByOpenId(Object key);

}