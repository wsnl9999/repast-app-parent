package com.qy105.aaa.mapper;

import com.qy105.aaa.model.CouponUser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface CouponUserMapper extends Mapper<CouponUser> {
    @Select("select @ from coupon_user where coupon_id = #{couponId} and user_id = #{openId}")
    CouponUser getCouponUserByCouponIdAndOpenId(int couponId,String openId);
}