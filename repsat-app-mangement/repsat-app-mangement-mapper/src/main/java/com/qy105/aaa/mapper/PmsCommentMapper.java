package com.qy105.aaa.mapper;

import com.qy105.aaa.model.PmsComment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsCommentMapper extends Mapper<PmsComment> {
    /**
     *  根据用户id获取用户评价
     * @author cat
     * @date 2020/3/15 13:50
     * @param memberid:
    * @return java.util.List<com.qy105.aaa.model.PmsComment>
     */
    List<PmsComment> qureyPmsCommentByMemberID(long memberid);

    /**
     *  根据id删除评论（逻辑删除）
     * @author cat
     * @date 2020/3/15 14:46
     * @param id:
    * @return java.lang.Integer
     */
    Integer deletePmsCommentById(long id);
}