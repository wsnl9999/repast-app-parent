package com.qy105.aaa.controller;

import com.qy105.aaa.base.BaseController;
import com.qy105.aaa.upload.FtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.qy105.aaa.staticstatus.StaticCode.RESULT;
import static com.qy105.aaa.staticstatus.StaticCode.TOKEN;

/**
 * @description:
 * @author: cat
 * @date: Created in 2020/3/17 13:49
 * @version: 1.0
 */
@RestController
public class UploadController extends BaseController {
    @Autowired
    private FtpService ftpService;

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean uploadFile(@RequestBody MultipartFile file, @RequestParam(TOKEN) String token) {
        Map<String, Object> upload = ftpService.upload(file, token);
        return (Boolean)upload.get(RESULT);
    }
}
