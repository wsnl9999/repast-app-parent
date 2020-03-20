package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.mapper.PmsCommentMapper;
import com.qy105.aaa.model.PmsComment;
import com.qy105.aaa.util.DateUtil;
import com.qy105.aaa.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.common.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.qy105.aaa.staticstatus.StaticCode.FORMAT_DATE;

/**
 * @description:
 * @author: cat
 * @date: Created in 2020/3/15 13:27
 * @version: 1.0
 */
@Service
public class PmsCommentService extends BaseService<PmsComment> {

    @Autowired
    private PmsCommentMapper pmsCommentMapper;
    @Override
    public Mapper<PmsComment> getMapper() {
        return pmsCommentMapper;
    }

    /**
     *  查询用户评级
     * @author cat
     * @date 2020/3/15 14:57
     * @param memberid:
    * @return com.qy105.aaa.base.ResultData
     */
    public ResultData qureyPmsCommentByMemberID(Long memberid){
        List<PmsComment> pmsComments = pmsCommentMapper.qureyPmsCommentByMemberID(memberid);
        ResultData resultData = new ResultData();
        resultData.setData(pmsComments);
        return resultData;
    };

    /**
     *  删除评价
     * @author cat
     * @date 2020/3/15 14:57
     * @param id:
    * @return java.lang.Boolean
     */
    public Boolean deletePmsCommentById(Long id){
        Integer integer = pmsCommentMapper.deletePmsCommentById(id);
        if (integer > 0 ){
            return true;
        }
        return false;
    }

    /**
     *  对商品进行评价
     * @author cat
     * @date 2020/3/16 16:01
     * @param pmsComment:
    * @return java.lang.Boolean
     */
    public Boolean addPmsComment(PmsComment pmsComment){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddr = IPUtil.getIpAddr(request);
        pmsComment.setMemberIp(ipAddr);
        pmsComment.setCreateTime(new Date());
        int i = pmsCommentMapper.updateByPrimaryKey(pmsComment);
        if (i > 0){
            return true;
        }
        return false;
    }

}
