package com.educationsystem.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educationsystem.demo.entity.College;
import com.educationsystem.demo.mapper.CollegeMapper;
import com.educationsystem.demo.service.CollegeService;
import com.educationsystem.demo.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService{

//    @Autowired
    @Resource //使用全局扫描后，用@Autowired会标红，但是没有影响@Resource不会
    CollegeMapper collegeMapper;

    public boolean save(College entity){
        if(StrUtil.isBlank(entity.getTeacher())){
           entity.setTeacher("待定");
        }
        return super.save(entity);
    }

//    public College selectByUsername(String collegename) {
////        QueryWrappe条件查询器，eq为等于
//        QueryWrapper<College> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", collegename);  //  eq => ==   where username = #{username}
//        // 根据用户名查询数据库的用户信息
//        return getOne(queryWrapper); //  select * from user where username = #{username}
//    }

//    @Override
//    public List<College> list() {
//        return collegeMapper.list();
//    }
//
//    @Override
//    public List<College> listByKey(String collegeKey) {
//        return collegeMapper.listByKey(collegeKey);
//    }

}
