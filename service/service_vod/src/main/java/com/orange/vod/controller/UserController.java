package com.orange.vod.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orange.ajaxresult.AjaxResult;
import com.orange.ajaxresult.Constants;
import com.orange.md5Utils.Md5Utils;
import com.orange.vod.domain.TempUser;
import com.orange.vod.domain.vo.LoginVo;
import com.orange.vod.domain.vo.RegisterVo;
import com.orange.vod.redis.RedisCache;
import com.orange.vod.service.TempUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    TempUserService tempuserService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginVo loginVo) {
        String uuid = loginVo.getUuid();
        String user = loginVo.getUser();
        String password = loginVo.getPassword();
        String code = loginVo.getCode();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        Object cacheObject = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (cacheObject == null) {
            return AjaxResult.error(407,"验证码已过期！");
        }
        if (!cacheObject.toString().equalsIgnoreCase(code)) {
            return AjaxResult.error(402,"验证码错误！");
        }
        TempUser tempuser = tempuserService.getOne(new QueryWrapper<TempUser>().eq("name", user));
        if (tempuser == null) {
            return AjaxResult.error(201, "用户名不存在！");
        } else {
            if (Md5Utils.getMD5Str(password).equals(tempuser.getPassword())) {
                return AjaxResult.success("登陆成功！", tempuser.getId());
            } else {
                return AjaxResult.error(4002,"密码错误！");
            }
        }
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @GetMapping("/info")
    public AjaxResult info(@ApiParam(name = "id", value = "ID", required = true, example = "1") Integer id) {
        TempUser userInfo = tempuserService.getById(id);
        return AjaxResult.success(userInfo);
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterVo registerVo) {
        String uuid = registerVo.getUuid();
        String nikeName = registerVo.getNickName();
        String email = registerVo.getEmail();
        String phone = registerVo.getPhone();
        String user = registerVo.getUser();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        // 校验验证码
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String TempCode = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (TempCode == null) {
            return AjaxResult.error(407,"验证码已过期！");
        }
        if (!TempCode.equalsIgnoreCase(code)) {
            return AjaxResult.error(402,"验证码错误！");
        }
        // 校验用户名是否存在
        TempUser tempuser = tempuserService.getOne(new LambdaQueryWrapper<TempUser>().eq(TempUser::getName, user));
        if (tempuser != null) {
            return AjaxResult.error(4001,"用户名已存在！");
        }
        // 注册用户
        TempUser tempUser1 = new TempUser();
        tempUser1.setName(user);
        tempUser1.setPassword(Md5Utils.getMD5Str(password));
        tempUser1.setNickName(nikeName);
        tempUser1.setEmail(email);
        tempUser1.setPhone(phone);
        boolean save = tempuserService.save(tempUser1);
        if (save) {
            return AjaxResult.success("注册成功！");
        } else {
            return AjaxResult.error("注册失败！请重试");
        }
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PostMapping("/updateUser")
    public AjaxResult updateAvatar(@RequestBody TempUser tempuser) {
        boolean update = tempuserService.updateById(tempuser);
        if (update) {
            return AjaxResult.success("修改成功！");
        } else {
            return AjaxResult.error("修改失败！请重试");
        }
    }
}
