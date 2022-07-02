package com.orange.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.ajaxresult.AjaxResult;
import com.orange.ajaxresult.StringUtils;
import com.orange.ggkt.model.vod.Teacher;
import com.orange.ggkt.vo.vod.TeacherQueryVo;
import com.orange.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public AjaxResult deleteById(@ApiParam(name = "id", value = "讲师ID", required = true,example = "1") Integer id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    // 分页查询讲师
    @PostMapping("/findAllPage")
    @ApiOperation(value = "分页查询讲师", notes = "分页查询讲师")
    public AjaxResult findByPage(@ApiParam(name = "teacherQueryVo", value = "讲师筛选信息",example = "")@RequestBody TeacherQueryVo teacherQueryVo) {
        Integer page = teacherQueryVo.getPage();
        Integer size = teacherQueryVo.getSize();
        Page<Teacher> pageDetail = new Page<>(page, size);
        IPage<Teacher> teacherList;
        System.out.println(teacherQueryVo);
        if (teacherService == null) {
            teacherList = teacherService.page(pageDetail, null);
        } else {
            QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            if (!StringUtils.isEmpty(name)) {
                teacherQueryWrapper.like("name", name);
            }
            if (level != null) {
                teacherQueryWrapper.eq("level", level);
            }
            if (!StringUtils.isEmpty(joinDateBegin)) {
                teacherQueryWrapper.ge("join_date", joinDateBegin);
            }
            if (!StringUtils.isEmpty(joinDateEnd)) {
                teacherQueryWrapper.le("join_date", joinDateEnd);
            }
            teacherList = teacherService.page(pageDetail, teacherQueryWrapper);
        }
        return AjaxResult.success(teacherList);
    }

    @GetMapping("/findById")
    @ApiOperation(value = "根据id查询讲师", notes = "根据id查询讲师")
    public AjaxResult findById(@ApiParam(name = "id", value = "讲师ID", required = true,example = "1") Integer id) {
        Teacher teacher = teacherService.getById(id);
        return AjaxResult.success(teacher);
    }

    @PostMapping("/updateTeacher")
    @ApiOperation(value = "更新讲师", notes = "更新讲师")
    public AjaxResult update(@ApiParam(name = "teacher", value = "讲师信息", required = true,example = "")@RequestBody Teacher teacher) {
        boolean b = teacherService.updateById(teacher);
        if (b) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @PostMapping("/addTeacher")
    @ApiOperation(value = "添加讲师", notes = "添加讲师")
    public AjaxResult add(@ApiParam(name = "teacher", value = "讲师信息", required = true,example = "")@RequestBody Teacher teacher) {
        boolean b = teacherService.save(teacher);
        if (b) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @PostMapping("/deleteTeacher")
    @ApiOperation(value = "批量删除讲师", notes = "批量删除讲师")
    public AjaxResult deleteTeacher(@ApiParam(name = "ids", value = "讲师ID集合", required = true,example = "1,2,3")@RequestBody List<Integer> ids) {
        boolean b = teacherService.removeByIds(ids);
        if (b) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }
}
