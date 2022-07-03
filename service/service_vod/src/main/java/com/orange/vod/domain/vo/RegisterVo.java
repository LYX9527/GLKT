package com.orange.vod.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package : com.orange.vod.domain.vo
 * @Author : yilantingfeng
 * @Date : 2022/7/2 10:29 PM
 * @Version : V1.0
 */
@Data
public class RegisterVo {

    @ApiModelProperty(value = "用户名", required = true)
    private String user;
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;
    @ApiModelProperty(value = "用户名", required = true)
    private String password;
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
    @ApiModelProperty(value = "uuid", required = true)
    private String uuid;
}
