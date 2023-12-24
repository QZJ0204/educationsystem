package com.educationsystem.demo.service;

import com.educationsystem.demo.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher fingadd(Teacher entity);

    Teacher useradd(String username, String name);

    Teacher findupdate(Teacher teacher);

    Teacher selectByid(String id);
//    List<Teacher> list();
}
