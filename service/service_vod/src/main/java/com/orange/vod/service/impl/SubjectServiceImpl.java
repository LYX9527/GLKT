package com.orange.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.ggkt.model.vod.Subject;
import com.orange.vod.mapper.SubjectMapper;
import com.orange.vod.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author EDY
 * @description 针对表【subject(课程科目)】的数据库操作Service实现
 * @createDate 2022-07-04 12:52:25
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
        implements SubjectService {

    @Override
    public List<Subject> getByParentId(Integer id) {
        LambdaQueryWrapper<Subject> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Subject::getParentId, id);
        List<Subject> subjectList = baseMapper.selectList(lambdaQueryWrapper);
        for (Subject subject : subjectList) {
            Boolean b = this.hasChild(subject.getId());
            if (b) {
                subject.setHasChildren(true);
            }
        }
        return subjectList;
    }

    private Boolean hasChild(Long id) {
        return baseMapper.selectCount(new LambdaQueryWrapper<Subject>().eq(Subject::getParentId, id)) > 0;
    }
}




