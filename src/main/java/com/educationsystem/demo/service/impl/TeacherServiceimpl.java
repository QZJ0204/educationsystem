package com.educationsystem.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educationsystem.demo.entity.Teacher;
import com.educationsystem.demo.mapper.CollegeMapper;
import com.educationsystem.demo.mapper.TeacherMapper;
import com.educationsystem.demo.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static cn.hutool.core.lang.Console.log;

@Service
public class TeacherServiceimpl extends ServiceImpl<TeacherMapper,Teacher> implements TeacherService {

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    CollegeMapper collegeMapper;

    @Override
    public Teacher fingadd(Teacher entity){
        entity.setId(String.valueOf(teacherMapper.selcetendId()+1));
//        log(entity.getId());
//        String name = entity.getName();
//        log(name);
//        entity.setCollegeId(collegeMapper.selcetcollege(name));
//        log(entity.getCollegeId());
        return entity;
    }

    @Override
    public Teacher useradd(String username,String name){
        Teacher teacher=new Teacher();
        teacher.setId(username);
        teacher.setTName(name);
        return teacher;
    }

    @Override
    public Teacher findupdate(Teacher teacher){
        String name = teacher.getName();
        teacher.setCollegeId(collegeMapper.selcetcollege(name));
        return teacher;
    }

    @Override
    public Teacher selectByid(String id){
        Teacher teacher = teacherMapper.selectByid(id);
        return teacher;
    }

    //    @Override
//    public List<Teacher> list() {
//        return teacherMapper.list();
//    }
}
