package com.orange.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.ggkt.model.vod.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author EDY
 * @description 针对表【subject(课程科目)】的数据库操作Mapper
 * @createDate 2022-07-04 12:52:25
 * @Entity com.orange.vod.domain.Subject
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    int delById(@Param("id") Long id);

    List<Subject> getById(@Param("id") Long id);



}




