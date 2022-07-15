package com.orange.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.ggkt.model.vod.VideoVisitor;
import com.orange.vod.domain.vo.VideoVisitorTestVo;

import java.util.List;

/**
* @author EDY
* @description 针对表【video_visitor(视频来访者记录表)】的数据库操作Mapper
* @createDate 2022-07-15 15:11:33
* @Entity com.orange.vod.domain.VideoVisitor
*/
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorTestVo> selectAll();
}




