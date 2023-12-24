package com.educationsystem.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educationsystem.demo.entity.Lesson;
import com.educationsystem.demo.mapper.LessonMapper;
import com.educationsystem.demo.mapper.TeacherMapper;
import com.educationsystem.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceimpl extends ServiceImpl<LessonMapper,Lesson> implements LessonService {

    @Resource
    LessonMapper lessonMapper;

    public List<Lesson> relist(List<Lesson> lessons){
        List<Lesson> lessonList =new ArrayList<>();
        for(Lesson lesson:lessons){
            Lesson form=new Lesson();
            form.setId(lesson.getId());
            form.setCourceId(lesson.getCourceId());
            form.setTeacherId(lesson.getTeacherId());
            form.setTerm(lesson.getTerm());
            form.setCName(lesson.getCource().getCName());
            form.setGrade(lesson.getCource().getGrade());
            form.setType(lesson.getCource().getType());
            form.setTName(lesson.getTeacher().getTName());
            lessonList.add(form);
        }
        return lessonList;
    }

    @Override
    public List<Lesson> list() {
        List<Lesson> list = lessonMapper.list();
        return relist(list);
    }

}
