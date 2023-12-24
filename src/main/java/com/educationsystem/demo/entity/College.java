package com.educationsystem.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//mybatis-puls包里的注解
@TableName("college")
public class College {
//    学员编号、学院名称、院系主任
    @TableId(type= IdType.AUTO)//表明字段自增
    private Integer id;
    private String name;
    private String teacher;
}
