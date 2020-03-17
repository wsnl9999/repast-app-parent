package com.qy105.aaa.controller;

import com.qy105.aaa.service.IRepastService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: cat
 * @date: Created in 2020/3/17 13:34
 * @version: 1.0
 */
@RestController
@Api(value = "文件上传", tags = "文件上传接口")
public class UploadController {

    @Autowired
    private IRepastService iRepastService;

}
