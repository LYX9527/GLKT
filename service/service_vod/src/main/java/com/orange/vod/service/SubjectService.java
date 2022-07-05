package com.orange.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.ggkt.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author EDY
* @description 针对表【subject(课程科目)】的数据库操作Service
* @createDate 2022-07-04 12:52:25
*/
public interface SubjectService extends IService<Subject> {

    List<Subject> getByParentId(Integer id);

    void export(HttpServletResponse response);

    void importData(MultipartFile file);
}
