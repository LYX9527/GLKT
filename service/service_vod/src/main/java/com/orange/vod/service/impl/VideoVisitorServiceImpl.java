package com.orange.vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.orange.ggkt.model.vod.VideoVisitor;
import com.orange.vod.domain.vo.VideoVisitorTestVo;
import com.orange.vod.mapper.VideoVisitorMapper;
import com.orange.vod.service.VideoVisitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EDY
 * @description 针对表【video_visitor(视频来访者记录表)】的数据库操作Service实现
 * @createDate 2022-07-15 15:11:33
 */
@Service
@Slf4j
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor>
        implements VideoVisitorService {

    @Resource
    private VideoVisitorMapper videoVisitorMapper;

    @Override
    public List<VideoVisitorTestVo> selectAll() {
        List<VideoVisitorTestVo> objects = videoVisitorMapper.selectAll();
        PageHelper.startPage(1, 10);
        return objects;
    }
}




