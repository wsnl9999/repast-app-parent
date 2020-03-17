package com.qy105.aaa.fallback;

import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.model.Coupon;
import com.qy105.aaa.model.LoginLog;
import com.qy105.aaa.model.Member;
import com.qy105.aaa.model.PmsComment;
import com.qy105.aaa.service.IRepastService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/11 17:36
 * @description：
 * @modified By：
 */
@Component
public class RepastFallBackFactory implements FallbackFactory<IRepastService> {
    @Override
    public IRepastService create(Throwable throwable) {
        IRepastService iRepastService = new IRepastService(){
            @Override
            public Boolean doLogin(Member member) {
                System.out.println("熔断登录方法");
                return null;
            }

            @Override
            public Boolean doLoginOut(String token) {
                System.out.println("熔断登出方法");
                return null;
            }


            @Override
            public Boolean saveLog(LoginLog loginLog) {
                System.out.println("熔断日志方法");
                return null;
            }

            @Override
            public List<Map> getIntegrationByToken(String token) {
                System.out.println("熔断积分获取方法");
                return null;
            }
            @Override
            public List<Coupon> getAllCoupon() {
                System.out.println("熔断查询优惠券方法");
                return null;
            }

            @Override
            public Integer addCoupon(Coupon coupon) {
                System.out.println("熔断领取优惠券方法");
                return null;
            }

            @Override
            public Boolean tokenCheck() {
                System.out.println("熔断检查方法");
                return null;
            }

            @Override
            public List<Coupon> selectCouponByOpenId() {
                System.out.println("熔断查询用户优惠券方法");
                return null;
            }

            @Override
            public void test() {
                System.out.println("------------------");

            }

            @Override
            public ResultData qureyPmsCommentByMemberID(Long memberid) {
                System.out.println("熔断查询用户评论方法");
                return null;
            }

            @Override
            public Boolean deletePmsCommentById(Long id) {
                System.out.println("熔断删除用户评论方法");
                return null;
            }

            @Override
            public Boolean addPmsComment(MultipartFile file, PmsComment pmsComment) {
                System.out.println("熔断增加用户评论方法");
                return null;
            }

            @Override
            public Boolean uploadFile(MultipartFile file, String token) {
                System.out.println("熔断文件上传");
                return null;
            }
        };
        return iRepastService;
    }
}
