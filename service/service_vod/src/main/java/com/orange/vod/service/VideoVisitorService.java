package com.orange.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.ggkt.model.vod.VideoVisitor;
import com.orange.vod.domain.vo.VideoVisitorTestVo;

import java.util.List;

/**
* @author EDY
* @description 针对表【video_visitor(视频来访者记录表)】的数据库操作Service
* @createDate 2022-07-15 15:11:33
*/
public interface VideoVisitorService extends IService<VideoVisitor> {

    List<VideoVisitorTestVo> selectAll();
}
