package com.educationsystem.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educationsystem.demo.entity.Selectscou;
import com.educationsystem.demo.mapper.SelectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SelectServiceImpl extends ServiceImpl<SelectMapper, Selectscou> {
    @Resource
    SelectMapper selectMapper;
}
