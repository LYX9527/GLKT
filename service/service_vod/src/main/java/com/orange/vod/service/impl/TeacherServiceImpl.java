package com.orange.vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.orange.ggkt.model.vod.Teacher;
import com.orange.vod.service.TeacherService;
import com.orange.vod.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

/**
* @author EDY
* @description 针对表【teacher(讲师)】的数据库操作Service实现
* @createDate 2022-07-01 12:40:08
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

}




