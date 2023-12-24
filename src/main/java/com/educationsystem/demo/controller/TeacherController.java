package com.educationsystem.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationsystem.demo.entity.*;
import com.educationsystem.demo.service.impl.TeacherServiceimpl;
import com.educationsystem.demo.service.impl.UserServiceImpl;
import com.educationsystem.demo.tool.Result;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherServiceimpl teacherServiceimpl;

    @Autowired
    UserServiceImpl userService;

//    @GetMapping("/teacher")
//    public Result list(){
//        List<Teacher> TeacherList=teacherServiceimpl.list();
//        return Result.sucess(TeacherList);
//    }
    @GetMapping("/selectALL")
    public Result selectALL(){
        List<Teacher>  teacherList = teacherServiceimpl.list(new QueryWrapper<Teacher>().orderByDesc("id"));
        return Result.sucess(teacherList);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher){
        Teacher teacher1 = teacherServiceimpl.fingadd(teacher);
//        if(StrUtil.isBlank(teacher1.getId())){
//            return Result.error("所属学院未知！");
//        }
        if(StrUtil.isBlank(teacher.getTName())){
            return Result.error("缺少数据,插入失败！");
        }else {
            try {
//            mybatis-plus中封装的插入方法
                userService.register1(teacher1.getId(),"教师");
                teacherServiceimpl.save(teacher1);
            } catch (Exception e) {
                log.error(String.valueOf(e));
                // 如果教师信息插入失败，回滚用户插入，即删除已插入的用户
                userService.removeById(teacher1.getId());
                return Result.error("插入失败！");
            }
        }
        return Result.sucess();
    }

    @PutMapping("/update1")
    public Result update1(@RequestBody Teacher teacher){
        teacherServiceimpl.updateById(teacher);
        return Result.sucess();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Teacher teacher){
        Teacher teacher1 = teacherServiceimpl.findupdate(teacher);
        if(StrUtil.isBlank(teacher1.getId())){
            return Result.error("所属学院未知！");
        }
        if(StrUtil.isBlank(teacher.getTName())){
            return Result.error("缺少数据,更新失败！");
        }else {
            try {
//            mybatis-plus中封装的插入方法
                teacherServiceimpl.updateById(teacher1);
            } catch (Exception e) {
                return Result.error("更新失败！");
            }
        }
        return Result.sucess();
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id){
        User user=userService.selectByUsername(id);
        try {
            userService.removeById(user.getId());
            teacherServiceimpl.removeById(id);
        }catch (Exception e){
            return Result.error("删除失败！");
        }
        return Result.sucess();
    }


    @DeleteMapping("/delete/batch")
    public Result delbatch(@RequestBody List<String> ids){
        List<User> usersToDelete = ids.stream()
                .map(id -> userService.selectByUsername(id))
                .filter(user -> user != null)
                .collect(Collectors.toList());
        try {
            usersToDelete.forEach(user -> userService.removeById(user.getId()));
        }catch (Exception e){
            return Result.error("批量删除失败！");
        }
        teacherServiceimpl.removeByIds(ids);
        return Result.sucess();
    }


    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String tName,
                               @RequestParam String pro){
        MPJLambdaWrapper<Teacher> queryWrapper =new MPJLambdaWrapper<Teacher>().orderByDesc(Teacher::getId);
        queryWrapper.selectAll(Teacher.class)
                .select(College::getName)
                .leftJoin(College.class,College::getId,Teacher::getCollegeId);
        queryWrapper.like(StrUtil.isNotBlank(tName),Teacher::getTName,tName);
        queryWrapper.like(StrUtil.isNotBlank(pro),Teacher::getPro,pro);
        Page<Teacher> page = teacherServiceimpl.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.sucess(page);
    }

    @GetMapping("/selectuser")
    public Result select(@RequestParam String username){
        Teacher teacher = teacherServiceimpl.selectByid(username);
        log.info(String.valueOf(teacher));
        return Result.sucess(teacher);
    }
}
