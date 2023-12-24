package com.educationsystem.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationsystem.demo.entity.College;
import com.educationsystem.demo.entity.Student;
import com.educationsystem.demo.entity.Teacher;
import com.educationsystem.demo.entity.User;
import com.educationsystem.demo.service.impl.StudentServiceImpl;
import com.educationsystem.demo.service.impl.TeacherServiceimpl;
import com.educationsystem.demo.service.impl.UserServiceImpl;
import com.educationsystem.demo.tool.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//提供接口返回数据
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    TeacherServiceimpl teacherServiceimpl;

    @GetMapping("/selectALL")
    public Result list(){
        List<User> Userlist=userService.list();
        return Result.sucess(Userlist);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(StrUtil.isBlank(user.getUsername())|| StrUtil.isBlank(user.getPassword())){
            return Result.error("数据不合法！");
        }
        try {
            user = userService.login(user);
        }catch (Exception e){
            return Result.error("账号或者密码错误！" +
                    "");
        }
        return Result.sucess(user);
    }


    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if(StrUtil.isBlank(user.getUsername())|| StrUtil.isBlank(user.getPassword())){
            return Result.error("数据不合法！");
        }
        if(user.getUsername().length()<2 && user.getUsername().length()>9){
            return Result.error("用户名不合法！");
        }
        user =userService.register(user);
        return Result.sucess(user);
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user){
//        user.setUrl("@/assets/默认头像.png");
        log.info("正在添加用户");
        if("管理员".equals(user.getAuth())){
            userService.save(user);
            log.info("管理员用户添加成功！");
        }
        else if("教师".equals(user.getAuth())){
            try{
                //添加教师信息
                Teacher teacher = teacherServiceimpl.useradd(user.getUsername(),user.getName());
                teacherServiceimpl.save(teacher);
                userService.save(user);
                log.info("教师用户添加成功！");
            }catch (Exception e){
                userService.removeById(user.getId());
                return Result.error("用户添加失败");
            }
        }
        else if("学生".equals(user.getAuth())){
            try{
//                添加学生信息
                Student student = studentService.useradd(user.getUsername(),user.getName());
                studentService.save(student);
                log.info("学生信息保存成功！");
                userService.save(user);
                log.info("用户信息保存成功！");
                log.info("学生用户添加成功！");
            }catch (Exception e){
                log.error(String.valueOf(e));
                userService.removeById(user.getId());
                return Result.error("用户添加失败");
            }
        }
        else {
            return Result.error("数据错误，请检查！");
        }
        return Result.sucess();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        User user = userService.selectById(id);
        log.info(String.valueOf(user));
        String nameID=user.getUsername();
        if("管理员".equals(user.getAuth())){
            userService.removeById(id);
            log.info("管理员用户删除成功！");
        }
        else if("教师".equals(user.getAuth())){
            try{
                teacherServiceimpl.removeById(nameID);
                log.info("教师用户删除成功！");
            }catch (Exception e){
                return Result.error("用户添加失败");
            }
        }
        else if("学生".equals(user.getAuth())){
            try{
                studentService.removeById(nameID);
                log.info("学生用户删除成功！");
            }catch (Exception e){
                return Result.error("用户添加失败");
            }
        }
        else {
            return Result.error("数据错误，请检查！");
        }
        userService.removeById(id);
        return Result.sucess();
    }


    @DeleteMapping("/delete/batch")
    public Result delbatch(@RequestBody List<Integer> ids){
//        这是Java中一个用于集合的语句。.collect(Collectors.toList()) 是Java Stream API的一部分，用于将stream中的元素收集到一个List中。
//        Stream API 是Java 8开始引入的一个新特性，它允许你以声明式方式处理数据集合（比如，转换一个集合的元素，过滤一个集合，等等）。
//        Collectors.toList() 是一个Collector，它会把流中的元素收集到一个List中。
        List<User> usersToDelete = ids.stream()
                .map(id -> userService.selectById(id))
                .filter(user -> user != null)
                .collect(Collectors.toList());
        usersToDelete.forEach(user -> {
            switch (user.getAuth()) {
                case "管理员":
                    userService.removeById(user.getId());
                    log.info("管理员用户删除成功！");
                    break;
                case "教师":
                    try {
                        teacherServiceimpl.removeById(user.getUsername());
                        log.info("教师用户删除成功！");
                    } catch (Exception e) {
                        Result.error("用户添加失败");
                    }
                    break;
                case "学生":
                    try {
                        // 添加学生信息
                        studentService.removeById(user.getUsername());
                        log.info("学生用户删除成功！");
                    } catch (Exception e) {
                         Result.error("用户添加失败");
                    }
                    break;
                default:
                    Result.error("数据错误，请检查！");
            }
        });
        userService.removeByIds(ids);
        return Result.sucess();
    }

    //管理员更改用户信息
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        String nameID=user.getUsername();
        if("管理员".equals(user.getAuth())){
            userService.updateById(user);
            log.info("管理员用户信息更改成功！");
        }
        else if("教师".equals(user.getAuth())){
            try{
                Teacher teacher = teacherServiceimpl.selectByid(nameID);
                teacher.setTName(user.getName());
                teacherServiceimpl.updateById(teacher);
                log.info("教师用户更新成功！");
            }catch (Exception e){
                return Result.error("用户更新失败");
            }
        }
        else if("学生".equals(user.getAuth())){
            try{
                Student student = studentService.selectByid(nameID);
                student.setName(user.getName());
                studentService.updateById(student);
                log.info("学生用户更新成功！");
            }catch (Exception e){
                return Result.error("用户更新失败");
            }
        }
        else {
            return Result.error("数据错误，请检查！");
        }
        userService.updateById(user);
        return Result.sucess();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String username,
                               @RequestParam String auth){
        QueryWrapper<User> queryWrapper =new QueryWrapper<User>().orderByDesc("id");
        queryWrapper.like(StrUtil.isNotBlank(username),"username",username);
        queryWrapper.like(StrUtil.isNotBlank(auth),"auth",auth);
        Page<User> page = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.sucess(page);
    }
}
