package com.orange.vod.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 临时用户表
 * @TableName tempuser
 */
@TableName(value ="tempuser")
@Data
public class Tempuser implements Serializable {
    /**
     * 主键id 用户编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @TableField(value = "name")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField(value = "phone")
    private String phone;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}