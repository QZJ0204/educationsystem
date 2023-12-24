package com.educationsystem.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educationsystem.demo.entity.Student;
import com.educationsystem.demo.entity.Teacher;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

//存放mybatis接口的地方
//@Mapper
public interface StudentMapper extends MPJBaseMapper<Student> {
//
////    插入学生信息
//    @Insert("insert into student (id,user_id,name,home,phone,sex,birth,endata,class_id)" +
//            "values (#{id},#{user_id},#{name},#{home},#{phone},#{sex},#{birth},#{endata},#{classId})")
//    void insert(Student student);
//
////    根据id更新学生信息
//    @Update("update student set name=#{name},home=#{home},phone=#{phone}," +
//            "sex=#{sex},birth=#{birth},endata=#{endata},class_id=#{classId} where user_id= #{userId}" )
//    void updateStudent(Integer userId);
//
//    查询全部学生信息
    @Select("select * from student")
    List<Student> list();

    @Select("select student.id, name, home, phone, sex, birth, endata, class_id,class_name from [student]\n" +
            "left join [class] c on student.class_id = c.id")
    List<Student> listall();

    //获取最后一条数据的id
    @Select("SELECT TOP 1 id FROM [student] ORDER BY id DESC;")
    int selcetendId();
//    @Select("SELECT id FROM [student] ORDER BY id DESC LIMIT 1;")
//    int selcetendId();

    @Select("SELECT * FROM [student] WHERE id=#{id}")
    Student selectByid(String id);
//
//    根据id删除
//    @Delete("delete from student where user_id=#{userId}")
//    void delectbyId(Integer id);

//    查询总记录数
//    @Select("select count(*) from student")
//    public Integer count();

//    分页查询，获取数据列表
//    @Select("SELECT * from student limit #{start}，#{pageSize}")
//    public List<Student> page(Integer start,Integer pageSize);

}
