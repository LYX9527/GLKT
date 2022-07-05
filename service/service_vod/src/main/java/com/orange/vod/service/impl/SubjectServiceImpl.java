package com.orange.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.errorcode.ErrorCode;
import com.orange.exception.CustomException;
import com.orange.ggkt.model.vod.Subject;
import com.orange.ggkt.vo.vod.SubjectEeVo;
import com.orange.vod.listener.SubjectEeVoListener;
import com.orange.vod.mapper.SubjectMapper;
import com.orange.vod.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
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

    @Override
    public void export(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String s = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + s + ".xlsx");
            List<Subject> subjectList = baseMapper.selectList(null);
            ArrayList<SubjectEeVo> subjectEeVoArrayList = new ArrayList<>();
            for (Subject subject : subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                subjectEeVo.setId(subject.getId());
                subjectEeVo.setParentId(subject.getParentId());
                subjectEeVo.setTitle(subject.getTitle());
                subjectEeVo.setSort(subject.getSort());
                subjectEeVoArrayList.add(subjectEeVo);
            }
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(subjectEeVoArrayList);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.EXPORT_FAIL,ErrorCode.EXPORT_FAIL_MSG);
        }

    }

    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectEeVo.class, new SubjectEeVoListener()).sheet().doRead();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.IMPORT_FAIL, ErrorCode.IMPORT_FAIL_MSG);
        }
    }

    private Boolean hasChild(Long id) {
        return baseMapper.selectCount(new LambdaQueryWrapper<Subject>().eq(Subject::getParentId, id)) > 0;
    }
}




