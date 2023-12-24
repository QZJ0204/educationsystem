package com.educationsystem.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educationsystem.demo.entity.ClassRoom;
import com.educationsystem.demo.mapper.ClassMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, ClassRoom> {
    @Resource
    ClassMapper classMapper;
}
