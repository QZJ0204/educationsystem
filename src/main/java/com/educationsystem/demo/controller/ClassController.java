package com.educationsystem.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.educationsystem.demo.entity.ClassRoom;
import com.educationsystem.demo.entity.Teacher;
import com.educationsystem.demo.service.impl.ClassServiceImpl;
import com.educationsystem.demo.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Resource
    ClassServiceImpl classService;

    @GetMapping("/selectALL")
    public Result selectALL(){
        List<ClassRoom> classList = classService.list(new QueryWrapper<ClassRoom>().orderByDesc("id"));
        return Result.sucess(classList);
    }
}
