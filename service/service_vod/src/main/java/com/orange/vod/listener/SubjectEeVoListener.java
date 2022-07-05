package com.orange.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.orange.ggkt.model.vod.Subject;
import com.orange.ggkt.vo.vod.SubjectEeVo;
import com.orange.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.vod.listener
 * @className : SubjectEeVoListener
 * @description:
 * @date : 2022/7/5 13:08
 */
@Component
public class SubjectEeVoListener extends AnalysisEventListener<SubjectEeVo> {
    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectEeVo, subject);
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
