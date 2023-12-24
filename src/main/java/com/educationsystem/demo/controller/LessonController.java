package com.educationsystem.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationsystem.demo.entity.ClassRoom;
import com.educationsystem.demo.entity.Cource;
import com.educationsystem.demo.entity.Lesson;
import com.educationsystem.demo.entity.Teacher;
import com.educationsystem.demo.service.impl.CourceServiceimpl;
import com.educationsystem.demo.service.impl.LessonServiceimpl;
import com.educationsystem.demo.service.impl.TeacherServiceimpl;
import com.educationsystem.demo.tool.Result;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    LessonServiceimpl lessonServiceimpl;

    @Autowired
    CourceServiceimpl courceServiceimpl;

    @Autowired
    TeacherServiceimpl teacherServiceimpl;

    /**
     * 查询所有课程
     * @return
     */
//    @GetMapping("/selectALL")
//    public Result selectALL(){
////        List<Lesson>  Lessonlist = lessonServiceimpl.list(new QueryWrapper<Lesson>().orderByDesc("id"));
//        List<Lesson> LessonList =lessonServiceimpl.list();
//        return Result.sucess(LessonList);
//    }


    @PostMapping("/add")
    public Result add(@RequestBody Lesson lesson){
        if(StrUtil.isBlank(lesson.getCName())|| StrUtil.isBlank(lesson.getGrade())||StrUtil.isBlank(lesson.getType())){
            return Result.error("缺少数据,插入失败！");
        }else {
            try {
                QueryWrapper<Teacher> teacher = new QueryWrapper<>();
                teacher.eq("t_name",lesson.getTName());
                Teacher one = teacherServiceimpl.getOne(teacher);
                if (one != null) {
                   lesson.setTeacherId(one.getId());
                } else {
                    log.error("任课老师无效！");
                    return Result.error("任课老师未知，更新失败！");
                }
                QueryWrapper<Cource> courceQueryWrapper=new QueryWrapper<>();
                courceQueryWrapper.eq("c_name",lesson.getCName())
                        .eq("grade",lesson.getGrade())
                        .eq("type",lesson.getType());
                Cource cource = courceServiceimpl.getOne(courceQueryWrapper);
                if(cource!=null){
                    lesson.setCourceId(cource.getId());
                }else {
                    log.error("课程无效！");
                    return Result.error("课程信息错误，更新失败！");
                }
                lessonServiceimpl.save(lesson);
            } catch (Exception e) {
                return Result.error("插入失败！");
            }
        }
        return Result.sucess();
    }

    /**
     *
     * @param lesson
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Lesson lesson){
        log.info(String.valueOf(lesson));
        if(StrUtil.isBlank(lesson.getCName())|| StrUtil.isBlank(lesson.getGrade())||StrUtil.isBlank(lesson.getType())){
            return Result.error("缺少数据,更新失败！");
        }else {
            try {
                QueryWrapper<Teacher> teacher = new QueryWrapper<>();
                teacher.eq("t_name", lesson.getTName());
                Teacher one = teacherServiceimpl.getOne(teacher);
                if (one != null) {
                    lesson.setTeacherId(one.getId());
                    log.info("任课老师更改");
                } else {
                    log.error("任课老师无效！");
                    return Result.error("任课老师未知，更新失败！");
                }

                QueryWrapper<Cource> courceQueryWrapper = new QueryWrapper<>();
                courceQueryWrapper.eq("c_name", lesson.getCName())
                        .eq("grade", lesson.getGrade())
                        .eq("type", lesson.getType());
                Cource cource = courceServiceimpl.getOne(courceQueryWrapper);
                if (cource != null) {
                    lesson.setCourceId(cource.getId());
                    lesson.setCName(cource.getCName()); // 根据需要设置其他属性的值
                    lesson.setGrade(cource.getGrade());
                    lesson.setType(cource.getType());
                    log.info("课程更改！");
                } else {
                    log.error("课程无效！");
                    return Result.error("课程信息错误，更新失败！");
                }
                lessonServiceimpl.updateById(lesson);
            } catch (Exception e) {
                return Result.error("更新失败！");
            }
        }
        return Result.sucess();
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        lessonServiceimpl.removeById(id);
        return Result.sucess();
    }


    @DeleteMapping("/delete/batch")
    public Result delbatch(@RequestBody List<Integer> ids){
        lessonServiceimpl.removeByIds(ids);
        return Result.sucess();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String cName,
                               @RequestParam String tName,
                               @RequestParam String type,
                               @RequestParam String term) {
        MPJLambdaWrapper<Lesson> queryWrapper =new MPJLambdaWrapper();
        queryWrapper.selectAll(Lesson.class)
                        .select(Cource::getCName,Cource::getType,Cource::getGrade)
                        .select(Teacher::getTName)
                        .select(ClassRoom::getClName)
                        .leftJoin(Cource.class,Cource::getId,Lesson::getCourceId)
                        .leftJoin(Teacher.class,Teacher::getId,Lesson::getTeacherId)
                        .leftJoin(ClassRoom.class,ClassRoom::getId,Lesson::getClassroomId)
                         .orderBy(true, true, Lesson::getId);
        queryWrapper.like(StrUtil.isNotBlank(cName),Cource::getCName,cName);
        queryWrapper.like(StrUtil.isNotBlank(term),Lesson::getTerm,term);
        queryWrapper.like(StrUtil.isNotBlank(tName),Teacher::getTName,tName);
        queryWrapper.like(StrUtil.isNotBlank(type),Cource::getType,type);
        Page<Lesson> page = lessonServiceimpl.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.sucess(page);
    }

    @GetMapping("/selectByTeacher")
    public Result selectByTeacher(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam String username) {
        MPJLambdaWrapper<Lesson> queryWrapper =new MPJLambdaWrapper();
        queryWrapper.selectAll(Lesson.class)
                .select(Cource::getCName,Cource::getType,Cource::getGrade)
                .select(Teacher::getTName)
                .select(ClassRoom::getClName)
                .leftJoin(Cource.class,Cource::getId,Lesson::getCourceId)
                .leftJoin(Teacher.class,Teacher::getId,Lesson::getTeacherId)
                .leftJoin(ClassRoom.class,ClassRoom::getId,Lesson::getClassroomId)
                .orderBy(true, true, Lesson::getId);
        queryWrapper.eq(Lesson::getTeacherId,username);
        Page<Lesson> page = lessonServiceimpl.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.sucess(page);
    }

}
