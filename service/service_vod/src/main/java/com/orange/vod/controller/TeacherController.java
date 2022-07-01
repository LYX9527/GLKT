package com.orange.vod.controller;

import com.orange.ajaxresult.AjaxResult;
import com.orange.ggkt.model.vod.Teacher;
import com.orange.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.vod.controller
 * @className : TeacherController
 * @description:
 * @date : 2022/7/1 12:41
 */
@Api(tags = "讲师接口")
@RestController
@RequestMapping("admin/vod/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    // 查询所有讲师
    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有讲师", notes = "查询所有讲师")
    public AjaxResult findAll() {
        List<Teacher> list = teacherService.list();
        return AjaxResult.success(list);
    }
    // 根据id删除讲师
    @GetMapping("/deleteById")
    @ApiOperation(value = "根据id删除讲师", notes = "根据id删除讲师")
    public AjaxResult deleteById(@ApiParam(name = "id", value = "讲师ID", required = true) Integer id) {
        boolean b = teacherService.removeById(id);
        if (b){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }
}
