package com.educationsystem.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("selectcou")
public class Selectscou {
//    序号、课程号、学号、成绩
    @TableId(type= IdType.AUTO)//表明字段自增
    private Integer sId;
    private Integer courceId;
    private String studentId;
    private String teacherId;
    private Integer score;

    @TableField(exist = false)
    private Cource cource;
    @TableField(exist = false)
    private Student student;
    @TableField(exist = false)
    private String id;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    public String cName;
    @TableField(exist = false)
    public String tName;
}
