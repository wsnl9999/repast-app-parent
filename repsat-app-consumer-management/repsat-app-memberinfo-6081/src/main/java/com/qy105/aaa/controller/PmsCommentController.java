package com.qy105.aaa.controller;

import com.qy105.aaa.annotation.TokenAnnotation;
import com.qy105.aaa.base.BaseController;
import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.model.PmsComment;
import com.qy105.aaa.service.IRepastService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: cat
 * @date: Created in 2020/3/15 13:26
 * @version: 1.0
 */
@RestController
@Api(value = "用户评价",tags = "用户评价接口")
public class PmsCommentController extends BaseController {
    @Autowired
    private IRepastService iRepastService;

    /**
     *  根据用户id查询用户的评论
     * @author cat
     * @date 2020/3/15 14:17
     * @param memberid:
    * @return com.qy105.aaa.base.ResultData
     */
    @TokenAnnotation
    @PostMapping("/qureyPmsCommentByMemberID")
    public ResultData qureyPmsCommentByMemberID(@RequestParam("memberid") Long memberid){
        ResultData resultData = iRepastService.qureyPmsCommentByMemberID(memberid);
        return resultData;
    }

    /**
     *  根据id删除评论（逻辑删除）
     * @author cat
     * @date 2020/3/15 14:50
     * @param id:
     * @return java.lang.Boolean
     */
    @TokenAnnotation
    @GetMapping("/deletePmsCommentById")
    public ResultData deletePmsCommentById(@RequestParam("id") Long id){
        Boolean aBoolean = iRepastService.deletePmsCommentById(id);
        if (aBoolean){
            return super.operationSuccess();
        }
        return super.operationFailed();
    };
    @TokenAnnotation
    @PostMapping("/addPmsComment")
    public ResultData addPmsComment(@RequestPart("file") MultipartFile file, @RequestPart("pmsComment") PmsComment pmsComment){
        Boolean aBoolean = iRepastService.addPmsComment(file, pmsComment);
        if (aBoolean){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
}
