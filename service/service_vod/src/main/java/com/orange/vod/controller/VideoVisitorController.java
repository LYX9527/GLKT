package com.orange.vod.controller;

import com.orange.ajaxresult.AjaxResult;
import com.orange.vod.domain.vo.VideoVisitorTestVo;
import com.orange.vod.service.VideoVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.vod.controller
 * @className : VideoVisitorController
 * @description:
 * @date : 2022/7/15 15:15
 */
@RestController
@RequestMapping("/admin/vod/videoVisitor")
public class VideoVisitorController {

    @Autowired
    private VideoVisitorService videoVisitorService;

    @GetMapping("/test")
    public AjaxResult test() {
        List<VideoVisitorTestVo> list = videoVisitorService.selectAll();
        return AjaxResult.success(list);
    }
}
