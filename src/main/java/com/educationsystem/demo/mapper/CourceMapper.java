package com.educationsystem.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educationsystem.demo.entity.Cource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface CourceMapper extends BaseMapper<Cource> {

//    @Select("SELECT * FROM cource")
//    List<Cource> list();

    //    根据id查询
    @Select("select name,type from [cource] where id=#{id}")
    void selectById(Integer id);
}
