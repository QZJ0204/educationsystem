package com.educationsystem.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("teacher")
public class Teacher {
    @TableId
    private String id;
    private String tName;
    private String phone;
    private String sex;
    private LocalDate endata;
    private String pro;
    private BigDecimal salary;
    private Integer collegeId;

    @TableField(exist = false)
    private String name;
}
