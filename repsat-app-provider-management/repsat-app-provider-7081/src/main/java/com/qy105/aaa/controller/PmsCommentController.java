package com.qy105.aaa.controller;

import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.model.PmsComment;
import com.qy105.aaa.upload.FtpService;
import com.qy105.aaa.service.PmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.qy105.aaa.staticstatus.StaticCode.*;

/**
 * @description:
 * @author: cat
 * @date: Created in 2020/3/15 13:26
 * @version: 1.0
 */
@RestController
public class PmsCommentController {
    @Autowired
    private PmsCommentService pmsCommentService;
    @Autowired
    private FtpService ftpService;

    @PostMapping("/qureyPmsCommentByMemberID")
    public ResultData qureyPmsCommentByMemberID(Long memberid){
        ResultData resultData = pmsCommentService.qureyPmsCommentByMemberID(memberid);
        return resultData;
    }

    @GetMapping("/deletePmsCommentById")
    public Boolean deletePmsCommentById(Long id){
        return pmsCommentService.deletePmsCommentById(id);
    }

    @PostMapping(value = "/addPmsComment",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addPmsComment(@RequestBody MultipartFile file, @RequestParam("pmsComment") PmsComment pmsComment){

        Map<String, Object> upload = ftpService.upload(file, null);
        if(!(Boolean)upload.get(RESULT)) {
            // 说明上传失败！！需要跳转到错误页面
            return false;
        } else {
            String headPic =  (String)upload.get(PICPATH);
            if(!"".equals(headPic)) {
                // 说明文件上传成功
                // 文件上传成功后，需要更新进用户的数据中
                pmsComment.setPics(headPic);
                Boolean aBoolean = pmsCommentService.addPmsComment(pmsComment);

                if (aBoolean) {
                    return true;
                }

            }
        }
        return false;
    }
}
