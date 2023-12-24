package com.educationsystem.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationsystem.demo.entity.ClassRoom;
import com.educationsystem.demo.entity.Student;
import com.educationsystem.demo.entity.User;
import com.educationsystem.demo.service.impl.StudentServiceImpl;
import com.educationsystem.demo.service.impl.UserServiceImpl;
import com.educationsystem.demo.tool.Result;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//解决vue和springboot端口不同的问题
//@CrossOrigin
//日志输出
@RestController
@Slf4j
@RequestMapping("/student")
public class StudentCotroller {

//    自动注入
    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    UserServiceImpl userService;

//    @GetMapping("/selectALL")
////    public Result selectALL(){
////        List<Student>  teacherList = studentService.list(new QueryWrapper<Student>().orderByDesc("id"));
////        return Result.sucess(teacherList);
////    }
    @GetMapping("/selectALL")
    public Result selectALL(){
        List<Student>  studentList = studentService.list();
        return Result.sucess(studentList);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Student student){
        Student student1=studentService.fingadd(student);
        if(StrUtil.isBlank(student.getName())){
            return Result.error("缺少数据,插入失败！");
        }else {
            try {
//            mybatis-plus中封装的插入方法
                userService.register1(student1.getId(),"学生");
                studentService.save(student1);
            } catch (Exception e) {
                log.error(String.valueOf(e));
                // 如果教师信息插入失败，回滚用户插入，即删除已插入的用户
                userService.removeById(student1.getId());
                return Result.error("插入失败！");
            }
        }
        return Result.sucess();
    }


    @PutMapping("/update")
    public Result update(@RequestBody Student student){
        studentService.updateById(student);
        return Result.sucess();
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id){
        User user = userService.selectByname(id);
        log.info(String.valueOf(user));
        try {
            userService.removeById(user.getId());
            studentService.removeById(id);
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
        studentService.removeByIds(ids);
        return Result.sucess();
    }


    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String id,
                               @RequestParam String name,
                               @RequestParam String clName){
        MPJLambdaWrapper<Student> queryWrapper =new MPJLambdaWrapper();
        queryWrapper.selectAll(Student.class)
                .select(ClassRoom::getClName)
                .leftJoin(ClassRoom.class, ClassRoom::getId,Student::getClassId)
                .orderByDesc(Student::getId);
        queryWrapper.like(StrUtil.isNotBlank(name),Student::getName,name);
        queryWrapper.like(StrUtil.isNotBlank(id),Student::getId,id);
        queryWrapper.like(StrUtil.isNotBlank(clName),ClassRoom::getClName,clName);
        Page<Student> page = studentService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.sucess(page);
    }

    @GetMapping("/selectuser")
    public Result select(@RequestParam String username){
        Student student = studentService.selectByid(username);
        log.info(String.valueOf(student));
        return Result.sucess(student);
    }
//    查询数据
//    @GetMapping()
//    public Result list(){
//        log.info("查询全部学生数据");
//        List<Student> studentList=studentService.list();
//        return Result.sucess(studentList);
//    }

//    @GetMapping
//    @RequestParam(defaultValue = "1")定义初始值
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize){
//        log.info("分页查询");
//        PageBean pageBean=studentService.page(page,pageSize);
//        return Result.sucess(pageBean);
//    }

//    删除数据
//    @DeleteMapping("/{id}")
//    public  Result delete(@PathVariable Integer id){
//        log.info("根据id删除学生信息{}",id);
//        studentService.delete(id);
//        return Result.sucess();
//    }
//
////    插入数据
//    @PostMapping()
//    public Result add(@RequestBody Student student){
//        log.info("添加学生信息");
//        studentService.insert(student);
//        return Result.sucess();
//    }
//
////    更新数据
//    @PutMapping("{id}")
//    public Result update(@PathVariable Integer id){
//        log.info("根据id更新学生信息{}",id);
//        studentService.update(id);
//        return Result.sucess();
//    }
}
