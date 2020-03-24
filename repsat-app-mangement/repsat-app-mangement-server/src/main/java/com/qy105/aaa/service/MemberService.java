package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.MemberMapper;
import com.qy105.aaa.model.Member;
import com.qy105.aaa.util.IDUtil;
import com.qy105.aaa.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService extends BaseService<Member> {

    @Autowired
    private MemberMapper memberMapper;

    private Member member;
    public Member getMember() {
        return member;
    }
    @Override
    public Mapper<Member> getMapper() {
        return memberMapper;
    }
    /**
     * create by: ws
     * description: TODO
     *              判断当前登录用户token是否为空
     * create time: 21:36 2020/3/13
     * * @Param: null
     * @return
     */
    public Boolean tokenCheck(){
        Member member = memberMapper.selectByPrimaryKey(this.member.getId());
        String token = member.getToken();
        if (token.equals("")||token==null) {
            return false;
        }
        return true;
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
                    this.member=one;
                    //不等于空说明不是新用户，需要更改token
                    one.setToken(token);
                    Integer update = super.update(one);
                    if(update>0){
                        return true;
                    }
                }else{

                    //one==空书名是新用户  需要添加token
                    member.setToken(token);
                    this.member=member;
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
    /**
     * create by: ws
     * description: TODO 查询用户积分操作
     * create time: 17:55 2020/3/14
     * * @Param: token
     * @return
     */
    public List<Map> getIntegrationByToken(String token) {
        List<Map> list = null;
        if (null != token) {
            try {
                list = memberMapper.getIntegrationByToken(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
