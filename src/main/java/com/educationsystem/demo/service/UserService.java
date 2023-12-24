package com.educationsystem.demo.service;

import com.educationsystem.demo.entity.User;

import java.util.List;

public interface UserService {
    User login(User user);

    User register(User user);

    User register1(String id, String auth);

    User selectById(Integer id);

    User selectByname(String name);

//    List<User> list();
}
