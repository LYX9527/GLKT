package com.orange.vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.ggkt.model.vod.Video;
import com.orange.vod.mapper.VideoMapper;
import com.orange.vod.service.VideoService;
import org.springframework.stereotype.Service;

/**
* @author EDY
* @description 针对表【video(课程视频)】的数据库操作Service实现
* @createDate 2022-07-15 13:46:46
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




