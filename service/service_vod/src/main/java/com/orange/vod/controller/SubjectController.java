package com.orange.vod.controller;

import com.orange.ajaxresult.AjaxResult;
import com.orange.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.vod.controller
 * @className : SubjectController
 * @description:
 * @date : 2022/7/4 13:06
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("admin/vod/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value = "分级查询课程", notes = "分级查询课程")
    @GetMapping("/findById")
    public AjaxResult findById(@ApiParam(name = "id", value = "课程ID", required = true, example = "1") Integer id) {
        return AjaxResult.success(subjectService.getByParentId(id));
    }

    @ApiOperation(value = "导出课程", notes = "导出课程")
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) {
        subjectService.export(response);
    }

    @ApiOperation(value = "导入课程", notes = "导入课程")
    @GetMapping("/import")
    public AjaxResult importData(MultipartFile file) {
        subjectService.importData(file);
        return AjaxResult.success("导入成功");
    }
}
