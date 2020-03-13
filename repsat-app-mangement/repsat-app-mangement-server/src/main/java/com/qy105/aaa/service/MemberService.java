package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.MemberMapper;
import com.qy105.aaa.model.Member;
import com.qy105.aaa.util.IDUtil;
import com.qy105.aaa.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
@Service
public class MemberService extends BaseService<Member> {

    @Autowired
    private MemberMapper memberMapper;
    @Override
    public Mapper<Member> getMapper() {
        return memberMapper;
    }
    /**
     * create by: ws
     * description: TODO 执行登录操作
     * create time: 17:55 2020/3/11
     * * @Param: null
     * @return
     */
    public Boolean doLogin(Member member){
        if(null != member && null != member.getOpenId() && StringUtil.isNotEmpty(member.getOpenId())) {
            try{
                Member one = memberMapper.getMemberByOpenId(member.getOpenId());
               // Member one = super.getOne(member);
                String token = IDUtil.getUUID() + member.getOpenId();
                if (one != null){
                    //不等于空说明不是新用户，需要更改token
                    one.setToken(token);
                    Integer update = super.update(one);
                    if(update>0){
                        return true;
                    }
                }else{
                    //one==空书名是新用户  需要添加token
                    member.setToken(token);
                    Integer insert = super.insert(member);
                    if(insert>0){
                        //添加token成功
                        return true;
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * create by: ws
     * description: TODO 执行退出登录操作
     * create time: 17:55 2020/3/11
     * * @Param: token
     * @return
     */
    public Boolean doLoginOut(String token) {
        if (null != token) {
            try {
                Integer update = memberMapper.doLoginOut(token);
                if (update>0){
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
