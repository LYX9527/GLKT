package com.orange.vod.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orange.ajaxresult.AjaxResult;
import com.orange.ajaxresult.Constants;
import com.orange.errorcode.ErrorCode;
import com.orange.exception.CustomException;
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
            throw new CustomException(ErrorCode.VERIFY_CODE_EXPIRED,ErrorCode.VERIFY_CODE_EXPIRED_MSG);
        }
        if (!cacheObject.toString().equalsIgnoreCase(code)) {
            throw new CustomException(ErrorCode.VERIFY_CODE_ERROR,ErrorCode.VERIFY_CODE_ERROR_MSG);
        }
        TempUser tempuser = tempuserService.getOne(new QueryWrapper<TempUser>().eq("name", user));
        if (tempuser == null) {
            throw new CustomException(ErrorCode.USER_NOT_EXIST, ErrorCode.USER_NOT_EXIST_MSG);
        } else {
            if (Md5Utils.getMD5Str(password).equals(tempuser.getPassword())) {
                return AjaxResult.success("登陆成功！", tempuser.getId());
            } else {
                throw new CustomException(ErrorCode.USERNAME_OR_PASSWORD_ERROR, ErrorCode.USERNAME_OR_PASSWORD_ERROR_MSG);
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
            return AjaxResult.error(ErrorCode.VERIFY_CODE_EXPIRED,ErrorCode.VERIFY_CODE_EXPIRED_MSG);
        }
        if (!TempCode.equalsIgnoreCase(code)) {
            return AjaxResult.error(ErrorCode.VERIFY_CODE_ERROR,ErrorCode.VERIFY_CODE_ERROR_MSG);
        }
        // 校验用户名是否存在
        TempUser tempuser = tempuserService.getOne(new LambdaQueryWrapper<TempUser>().eq(TempUser::getName, user));
        if (tempuser != null) {
            throw new CustomException(ErrorCode.USER_EXIST,ErrorCode.USER_EXIST_MSG);
        }
        // 注册用户
        TempUser registerUser = new TempUser();
        registerUser.setName(user);
        registerUser.setPassword(Md5Utils.getMD5Str(password));
        registerUser.setNickName(nikeName);
        registerUser.setEmail(email);
        registerUser.setPhone(phone);
        boolean save = tempuserService.save(registerUser);
        if (save) {
            return AjaxResult.success("注册成功！");
        } else {
            throw new CustomException(ErrorCode.REGISTER_FAIL,ErrorCode.REGISTER_FAIL_MSG);
        }
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PostMapping("/updateUser")
    public AjaxResult updateAvatar(@RequestBody TempUser tempuser) {
        boolean update = tempuserService.updateById(tempuser);
        if (update) {
            return AjaxResult.success("修改成功！");
        } else {
            throw new CustomException(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG);
        }
    }
}
