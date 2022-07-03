package com.orange.vod.controller;

import com.orange.ajaxresult.AjaxResult;
import com.orange.vod.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Key;
import java.util.HashMap;

/**
 * @Package : com.orange.vod.controller
 * @Author : yilantingfeng
 * @Date : 2022/7/3 2:32 PM
 * @Version : V1.0
 */
@Api(tags = "文件接口")
@RestController
@RequestMapping("/admin/vod/upload")
public class UploadFileController {
    @Autowired
    private UploadFileService uploadFileService;
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping("/file")
    public AjaxResult uploadFile(@RequestBody MultipartFile file) {
        HashMap<String,String> resultMap =  uploadFileService.uploadFile(file);
        return AjaxResult.success("上传成功！",resultMap);
    }

    @ApiOperation(value = "文件删除", notes = "文件删除")
    @PostMapping ("/delfile")
    public AjaxResult delFile(String key) {
        uploadFileService.delFile(key);
        return AjaxResult.success("删除成功！");
    }
}
