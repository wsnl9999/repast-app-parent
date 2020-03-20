package com.qy105.aaa.mapper;

import com.qy105.aaa.model.Member;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberMapper extends Mapper<Member> {
    @Select("select * from ums_member where open_id = #{openId}")
    Member getMemberByOpenId(String openId);
    @Select("select * from ums_member where id = #{id}")
    Member getMemberById(int id);

    List<Map> getIntegrationByToken(String token);
    @Update("update ums_member set token = null where token = #{token}")
    Integer doLoginOut(String token);
}