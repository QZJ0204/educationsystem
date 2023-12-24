package com.educationsystem.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educationsystem.demo.entity.Teacher;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface TeacherMapper extends MPJBaseMapper<Teacher> {
//    @Select("select * FROM teacher")
//    List<Teacher> list();

//获取最后一条数据的id
@Select("SELECT TOP 1 id FROM [teacher] ORDER BY id DESC;")
int selcetendId();
//@Select("SELECT id FROM [teacher] ORDER BY id DESC LIMIT 1;")
//int selcetendId();

@Select("SELECT * FROM [teacher] WHERE id=#{id}")
Teacher selectByid(String id);

}
