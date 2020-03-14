package com.qy105.aaa.mapper;

import com.qy105.aaa.model.Member;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {
    @Select("select * from ums_member where open_id = #{openId}")
    public Member getMemberByOpenId(String openId);
    @Update("update ums_member set token = null where token = #{token}")
    public Integer doLoginOut(String token);
}