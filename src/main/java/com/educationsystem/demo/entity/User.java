package com.educationsystem.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user1")
public class User {
    @TableId(type= IdType.AUTO)//表明字段自增
    public Integer id;
    private String username;
    private String password ="123456";
//    0为管理员，1为教师，2为学生
//    public String url;
    private String auth;

    @TableField(exist = false)
    public String token;
    @TableField(exist = false)
    public String name;
}
