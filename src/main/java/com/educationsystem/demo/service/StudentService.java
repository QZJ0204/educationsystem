package com.educationsystem.demo.service;

import com.educationsystem.demo.entity.Student;

import java.util.List;

public interface StudentService {
    Student fingadd(Student entity);

    Student useradd(String username, String name);

    Student selectByid(String id);
//    查询全部学生信息
//    List<Student> list();
//    void delete(Integer id);
//    void insert(Student student);
//    void update(Integer id);

}
