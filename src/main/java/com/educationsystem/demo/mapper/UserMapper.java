package com.educationsystem.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educationsystem.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface UserMapper extends BaseMapper<User> {
//    @Select("select * from user where username=#{username} ")
//    User selectByUserID(String username);

    @Select("SELECT * FROM [user1] WHERE username = #{username}")
    User selectByUsername(String username);
    //
    @Insert("insert into [user1] (username,password,auth) values (#{username},#{password},#{auth})")
    int insert(User user);
//    @Insert("insert into [user1] (username,password,auth) value (#{username},#{password},#{auth})")
//    int insert(User user);
//
    @Select("select * FROM [user1]")
    List<User> list();
//
    @Select("select * from [user1] where username=#{userId}")
    User selectById(String userId);

    @Select("SELECT * FROM [user1] where id=#{id}")
    User selectId(Integer id);

    @Select("SELECT * FROM [user1] where username=#{username}")
    User selectByname(String username);
}
