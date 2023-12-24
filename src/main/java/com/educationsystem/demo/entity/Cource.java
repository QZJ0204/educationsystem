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
@TableName("cource")
public class Cource {
//    课程编号、课程名称、课程类型、课程学分
    @TableId(type= IdType.AUTO)//表明字段自增
    public Integer id;
    public String cName;
    public String type;
    public String grade;
}
