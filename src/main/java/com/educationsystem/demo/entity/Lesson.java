package com.educationsystem.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lesson")
public class Lesson {
//    序号、课程号、教师号、学期
    @TableId(type= IdType.AUTO)//表明字段自增
    public Integer id;
    public Integer courceId;
    public String teacherId;
    public String classroomId;
    public String term;
    @TableField(exist = false)
    private Cource cource;
    @TableField(exist = false)
    private Teacher teacher;

//    mybatis可以不加，但是使用mybatis-puls一定要加，表示不在表单的字段
    @TableField(exist = false)
    public String cName;
    @TableField(exist = false)
    public String type;
    @TableField(exist = false)
    public String grade;
    @TableField(exist = false)
    public String tName;
    @TableField(exist = false)
    public String clName;

}
