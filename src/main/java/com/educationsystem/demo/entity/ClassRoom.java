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
@TableName("classroom")
public class ClassRoom {
//    班级号、班级名称、班主任、所属院系
    @TableId(type= IdType.AUTO)//表明字段自增
    private Integer id;
    private String clName;
    private String teacherId;
    private Integer collegeId;

//    /**
//     * 构造器
//     * @param Class
//     */
//    public Class(Class Class){
//        Optional.ofNullable(Class).ifPresent(e->{
//            this.id=e.getId();
//            this.name=e.getName();
//            this.teacherId=e.getTeacherId();
//            this.collegeId=e.getCollegeId();
//        });
//    }
}
