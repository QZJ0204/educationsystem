package com.educationsystem.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educationsystem.demo.entity.College;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper  ：MybatisPlusConfig类加了全局扫描可以不用加@Mapper

//如果是mybatis是没有继承BaseMapper<实体类名>的，需要写sql语句
public interface CollegeMapper extends BaseMapper<College> {
//    @Select("SELECT * FROM college")
//    List<College> list();

    //通过学院名称获取学院id
    @Select("select id from [college] where name=#{name}")
    int selcetcollege(String name);
}

