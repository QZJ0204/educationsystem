package com.educationsystem.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educationsystem.demo.entity.User;
import com.educationsystem.demo.mapper.UserMapper;
import com.educationsystem.demo.service.UserService;
import com.educationsystem.demo.tool.TokenUtils;
import com.google.protobuf.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.lang.Console.log;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Resource
    UserMapper userMapper;

    public User selectByUsername(String username) {
        User user = userMapper.selectByUsername(username);
//        log.info(String.valueOf(user));
        return user;
////        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);  //  eq => ==   where username = #{username}
//        // 根据用户名查询数据库的用户信息
//        return getOne(queryWrapper); //  select * from user where username = #{username}
    }


    //    验证账户是否合法
    @Override
    public User login(User user) {
        log.info(String.valueOf(user.getUsername()));
//        User user1 = selectByUsername(user.getUsername());
        User user1 = userMapper.selectByUsername(user.getUsername());
        log.info(String.valueOf(user1));
        if(user1 ==null){
            try {
                throw new ServiceException("账号不存在");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        if(!user.getPassword().equals(user1.getPassword())){
            {
                try {
                    throw new ServiceException("用户名或密码错误");
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        String token = TokenUtils.createToken(user1.getUsername(),user1.getPassword());
        user1.setToken(token);

        return user1;
    }

    @Override
    public User register(User user) {
//        User user1= userMapper.selectByUserID(user.getUsername());
//        User user1 = selectByUsername(user.getUsername());
        User user1 = userMapper.selectByUsername(user.getUsername());
        if(user1 !=null){
            try {
//                log.error("用户名已存在！");
                throw new ServiceException("用户名已存在！");
            } catch (ServiceException e) {
//            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        userMapper.insert(user);
        return user;
    }

    @Override
    public User register1(String id, String auth){
        User user = new User();
        user.setUsername(id);
        user.setAuth(auth);
        userMapper.insert(user);
        return user;
    }
//    @Override
//    public List<User> list() {
//        List<User> users =userMapper.list();
//        return users;
//    }

    @Override
    public User selectById(Integer id){
        User user = userMapper.selectId(id);
        return user;
    }

    @Override
    public User selectByname(String name){
        User user=userMapper.selectByname(name);
        return user;
    }
}
