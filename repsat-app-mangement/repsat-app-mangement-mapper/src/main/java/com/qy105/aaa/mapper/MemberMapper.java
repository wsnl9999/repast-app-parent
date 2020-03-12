package com.qy105.aaa.mapper;

import com.qy105.aaa.model.Member;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {
    @Select("select * from ums_member where open_id = #{openId}")
    public Member getMemberByOpenId(String openId);
}