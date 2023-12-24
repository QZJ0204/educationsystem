package com.educationsystem.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student")
public class Student {
    @TableId()//表明字段自增
    private String id;
    private String name;
    private String home;
    private String phone;
    private String sex;
    private LocalDate birth;
    private LocalDate endata;
    private Integer classId;

    @TableField(exist = false)
    private String clName;
//    public Student(Student student){
//        if(Objects.nonNull(student)){
//            this.id=student.id;
//            this.name=student.name;
//            this.home=student.home;
//            this.phone=student.phone;
//            this.sex=student.sex;
//            this.birth=student.birth;
//            this.endata=student.endata;
//            this.classId=student.classId;
//
//        }
//    }
}
