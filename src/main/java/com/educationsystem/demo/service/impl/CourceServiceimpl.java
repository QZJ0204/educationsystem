package com.educationsystem.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educationsystem.demo.entity.College;
import com.educationsystem.demo.entity.Cource;
import com.educationsystem.demo.mapper.CollegeMapper;
import com.educationsystem.demo.mapper.CourceMapper;
import com.educationsystem.demo.service.CourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourceServiceimpl extends ServiceImpl<CourceMapper,Cource> {

    @Resource
    CourceMapper courceMapper;


//    @Override
//    public List<Cource> list() {
//        return courceMapper.list();
//    }
}
