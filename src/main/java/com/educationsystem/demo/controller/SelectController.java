package com.educationsystem.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationsystem.demo.entity.*;
import com.educationsystem.demo.service.impl.CourceServiceimpl;
import com.educationsystem.demo.service.impl.SelectServiceImpl;
import com.educationsystem.demo.service.impl.StudentServiceImpl;
import com.educationsystem.demo.tool.Result;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/select")
public class SelectController {

    @Autowired
    SelectServiceImpl selectService;
    @Autowired
    CourceServiceimpl courceServiceimpl;
    @Autowired
    StudentServiceImpl studentService;

    @PostMapping("/add")
    public Result add(@RequestBody Selectscou selectscou){
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.eq("name",selectscou.getName())
                        .eq("id",selectscou.getId());
                Student one = studentService.getOne(studentQueryWrapper);
                if (one != null) {
                    selectscou.setStudentId(one.getId());
                    log.info("查询学生成功！");
                } else {
                    log.error("学生无效！");
                }
                QueryWrapper<Cource> courceQueryWrapper=new QueryWrapper<>();
                courceQueryWrapper.eq("c_name",selectscou.getCName());
                Cource cource = courceServiceimpl.getOne(courceQueryWrapper);
                if(cource!=null){
                    selectscou.setCourceId(cource.getId());
                    log.info("查询课程成功！");
                }else {
                    log.error("课程无效！");
                }
                selectService.save(selectscou);
        return Result.sucess();
    }

    @PostMapping("/add1")
    public Result add1(@RequestBody Selectscou selectscou){
        log.info(String.valueOf(selectscou));
        selectService.save(selectscou);
        return Result.sucess();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Selectscou selectscou) {
        log.info("课程数据更新...");
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("name",selectscou.getName())
                .eq("id",selectscou.getId());
        Student one = studentService.getOne(studentQueryWrapper);
        if (one != null) {
            selectscou.setStudentId(one.getId());
            log.info("查询学生成功！");
        } else {
            log.error("学生无效！");
            return Result.error("学生信息未知，更新失败！");
        }
        QueryWrapper<Cource> courceQueryWrapper=new QueryWrapper<>();
        courceQueryWrapper.eq("c_name",selectscou.getCName());
        Cource cource = courceServiceimpl.getOne(courceQueryWrapper);
        if(cource!=null){
            selectscou.setCourceId(cource.getId());
            log.info("查询课程成功！");
        }else {
            log.error("课程无效！");
            return Result.error("课程信息错误，更新失败！");
        }
        selectService.updateById(selectscou);
        return Result.sucess();
    }

    @PutMapping("/update1")
    public Result update1(@RequestBody Selectscou selectscou) {
        try {
            log.info("正在更新...");
            selectService.updateById(selectscou);
        }catch (Exception e){
            log.error(String.valueOf(e));
        }
        return Result.sucess();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        selectService.removeById(id);
        return Result.sucess();
    }


    @DeleteMapping("/delete/batch")
    public Result delbatch(@RequestBody List<Integer> ids){
        selectService.removeByIds(ids);
        return Result.sucess();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String cName,
                               @RequestParam String name,
                               @RequestParam String teacherId,
                               @RequestParam String id,
                               @RequestParam String score) {
        MPJLambdaWrapper<Selectscou> queryWrapper =new MPJLambdaWrapper();
//        queryWrapper.select(Selectscou::getSId,Selectscou::getCourceId,Selectscou::getStudentId,Selectscou::getScore)
        queryWrapper.selectAll(Selectscou.class)
                .select(Cource::getCName)
                .select(Student::getId,Student::getName)
                .select(Teacher::getTName)
                .leftJoin(Cource.class,Cource::getId,Selectscou::getCourceId)
                .leftJoin(Teacher.class,Teacher::getId,Selectscou::getTeacherId)
                .leftJoin(Student.class,Student::getId,Selectscou::getStudentId)
                .orderBy(true, true, Selectscou::getSId);
        queryWrapper.like(StrUtil.isNotBlank(cName),Cource::getCName,cName);
        queryWrapper.like(StrUtil.isNotBlank(name),Student::getName,name);
        queryWrapper.like(StrUtil.isNotBlank(id),Student::getId,id);
        queryWrapper.like(StrUtil.isNotBlank(teacherId),Selectscou::getTeacherId,teacherId);
        queryWrapper.like(StrUtil.isNotBlank(score),Selectscou::getScore,score);
        Page<Selectscou> page = selectService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.sucess(page);
    }
//    public Result selectByPage(@RequestParam Integer pageNum,
//                               @RequestParam Integer pageSize,
//                               @RequestParam String cName,
//                               @RequestParam String name,
//                               @RequestParam String teacherId,
//                               @RequestParam String id,
//                               @RequestParam String score) {
//        MPJLambdaWrapper<Selectscou> queryWrapper =new MPJLambdaWrapper();
////        queryWrapper.select(Selectscou::getSId,Selectscou::getCourceId,Selectscou::getStudentId,Selectscou::getScore)
//        queryWrapper.selectAll(Selectscou.class)
//                .select(Cource::getCName)
//                .select(Student::getId,Student::getName)
//                .select(Teacher::getTName)
//                .leftJoin(Cource.class,Cource::getId,Selectscou::getCourceId)
//                .leftJoin(Teacher.class,Teacher::getId,Selectscou::getTeacherId)
//                .leftJoin(Student.class,Student::getId,Selectscou::getStudentId);
//        queryWrapper.like(StrUtil.isNotBlank(cName),Cource::getCName,cName);
//        queryWrapper.like(StrUtil.isNotBlank(name),Student::getName,name);
//        queryWrapper.like(StrUtil.isNotBlank(id),Student::getId,id);
//        queryWrapper.like(StrUtil.isNotBlank(teacherId),Selectscou::getTeacherId,teacherId);
//        queryWrapper.like(StrUtil.isNotBlank(score),Selectscou::getScore,score);
//        Page<Selectscou> page = selectService.page(new Page<>(pageNum, pageSize), queryWrapper);
//        return Result.sucess(page);
//    }

}
