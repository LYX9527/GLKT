package com.orange.vod.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.vod.domain.vo
 * @className : VideoVisitorVo
 * @description:
 * @date : 2022/7/15 16:21
 */
@Data
public class VideoVisitorTestVo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "视频id")
    private Long videoId;

    @ApiModelProperty(value = "视频名称")
    private String videoName;
}
