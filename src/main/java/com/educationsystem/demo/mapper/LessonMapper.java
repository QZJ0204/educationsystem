package com.educationsystem.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educationsystem.demo.entity.Lesson;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
public interface LessonMapper extends MPJBaseMapper<Lesson> {

    @Select("select lesson.id, cource_id, teacher_id, term,cource.name,cource.type,cource.grade,teacher.name \n" +
            "from [lesson]  join [cource] on cource_id=cource.id\n" +
            "join [teacher] on teacher_id =teacher.id")
    List<Lesson> list();

//    @Select("SELECT l.id,cource_id,teacher_id, c.c_name AS cName,c.grade,c.type,term,t.t_name AS tName " +
//            "FROM [lesson] l " +
//            "JOIN [cource] c ON l.cource_id = c.id " +
//            "JOIN [teacher] t ON l.teacher_id = t.id " )
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "cource_id", property = "courceId"),
//            @Result(column = "teacher_id", property = "teacherId"),
//            @Result(column = "cName", property = "cource.c_name"),
//            @Result(column = "grade", property = "cource.grade"),
//            @Result(column = "type", property = "cource.type"),
//            @Result(column = "tName", property = "teacher.t_name"),
//            @Result(column = "term", property = "term")
//    })
//    List<Lesson> list();

//    @Select("select * from lesson")
//    @Results({
//            @Result(column = "id",property = "id"),
//            @Result(column = "term",property = "term"),
//            @Result(column = "courceId",property = "cName",javaType = List.class,
//            one=@One(select = "com/educationsystem/demo/mapper/CourceMapper.selectById")),
//            @Result(column = "name",property = "tName",javaType = List.class,
//                    one=@One(select = "com/educationsystem/demo/mapper/TeacherMapper.selectById"))
//    })
//    List<Lesson> list();


}
