package com.orange.vod.controller;

import com.orange.ajaxresult.AjaxResult;
import com.orange.vod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.vod.controller
 * @className : VideoController
 * @description:
 * @date : 2022/7/15 15:06
 */
@RestController
@RequestMapping("/admin/vod/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/test")
    public AjaxResult test() {
//        List<Object> videoList = videoService.selectAll();
        return AjaxResult.success(null);
    }
}
