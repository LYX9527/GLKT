package com.orange.vod.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package : com.orange.vod.domain.vo
 * @Author : yilantingfeng
 * @Date : 2022/7/2 9:30 PM
 * @Version : V1.0
 */
@Data
public class LoginVo {
    @ApiModelProperty(value = "用户名", required = true)
    private String user;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
    @ApiModelProperty(value = "uuid", required = true)
    private String uuid;
}
