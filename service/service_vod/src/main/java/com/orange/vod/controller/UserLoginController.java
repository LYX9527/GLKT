package com.orange.vod.controller;

import com.orange.ajaxresult.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Package : com.orange.vod.controller
 * @Author : yilantingfeng
 * @Date : 2022/7/2 6:47 PM
 * @Version : V1.0
 */
@RestController
@RequestMapping("admin/vod/user")
@Api(tags = "用户接口")
public class UserLoginController {

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public AjaxResult login() {
        return AjaxResult.success();
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @GetMapping("/info")
    public AjaxResult info() {
        return AjaxResult.success();
    }
}
