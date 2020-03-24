package com.qy105.aaa.scheduledservice;

import com.qy105.aaa.mapper.CouponUserMapper;
import com.qy105.aaa.model.CouponUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/15 0:09
 * @description：
 * @modified By：
 */
@Service
public class ScheduledService {
    @Autowired
    private CouponUserMapper couponUserMapper;

    public static void main(String[] args) {

    }

    /**
     * create by: ws
     * description: TODO
     *          每天凌晨十二点执行
     *          判断优惠券有没有过期
     *             过期就修改状态为0
     * create time: 0:47 2020/3/15
     * * @Param: null
     * @return
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkCouponStatus(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<CouponUser> couponUsers = couponUserMapper.selectAll();
        for (CouponUser user : couponUsers) {
            String endTime = user.getEndTime();
            try {
                Date parse = sdf.parse(endTime);
                int i = new Date().compareTo(parse);
                if ( i >= 0 ){
                    user.setStatus(0);
                    couponUserMapper.updateByPrimaryKey(user);

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public int useCoupon(int i) {

        return 0;
    }
}
