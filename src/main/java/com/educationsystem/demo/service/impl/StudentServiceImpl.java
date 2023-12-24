package com.educationsystem.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educationsystem.demo.entity.Student;
import com.educationsystem.demo.mapper.StudentMapper;
import com.educationsystem.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static cn.hutool.core.lang.Console.log;

@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {

    @Resource
    StudentMapper studentMapper;

    public List<Student> list(){
        return studentMapper.listall();
    }

    @Override
    public Student fingadd(Student entity){
        entity.setId(String.valueOf(studentMapper.selcetendId()+1));
        log(entity.getId());
        return entity;
    }

    @Override
    public Student useradd(String username,String name){
        Student student=new Student();
        student.setId(username);
        student.setName(name);
        log.info(String.valueOf(student));
        return student;
    }

    @Override
    public Student selectByid(String id){
        Student student = studentMapper.selectByid(id);
        return student;
    }

//
//    public void delete(Integer id) { studentMapper.delectbyId(id);
//    }
//
//    @Override
//    public void insert(Student student) {
//        studentMapper.insert(student);
//
//    }
//
//    @Override
//    public void update(Integer id) {
//        studentMapper.updateStudent(id);
//    }

}
