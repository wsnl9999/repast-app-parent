package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.CouponMapper;
import com.qy105.aaa.model.Coupon;
import com.qy105.aaa.model.CouponUser;
import com.qy105.aaa.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.qy105.aaa.staticstatus.StaticCode.FORMAT_DATE2;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/13 16:00
 * @description：
 * @modified By：
 */
@Service
public class CouponService extends BaseService<Coupon> {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponUserService couponUserService;

    @Override
    public Mapper<Coupon> getMapper() {
        return couponMapper;
    }

    /**
     * create by: ws
     * description: TODO
     * 获取所有优惠券
     * create time: 16:20 2020/3/13
     * * @Param: null
     * @return
     */
    public List<Coupon> getAllCoupon() {
        return getMapper().selectAll();
    }
    /**
     * create by: ws
     * description: TODO
     *  领取优惠券方法
     * create time: 14:23 2020/3/14
     * * @Param: null
     * @return
     */
    public Integer addCoupon(Coupon coupon, Member member){
        System.out.println(coupon.getId());
        Coupon coupon2 = getMapper().selectByPrimaryKey(coupon.getId());
        System.out.println(coupon2);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        c.add(Calendar.DATE,coupon2.getDuration());
        Date time = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE2);
        CouponUser couponUser = new CouponUser();
        couponUser.setCouponId(Long.valueOf(coupon.getId()));
        couponUser.setEndTime(sdf.format(time));
        couponUser.setStartTime(sdf.format(new Date()));
        System.out.println(sdf.format(time));
        System.out.println(sdf.format(new Date()));
        couponUser.setUserId(Long.valueOf(member.getOpenId()));
        couponUser.setStatus(1);
        return couponUserService.addCoupon(couponUser);

    }

    public List<Coupon> selectCouponByOpenId(Member member){
return null;
//       return couponMapper.selectCouponByOpenId(Long.valueOf(member.getOpenId()));

    }
}
