package com.educationsystem.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationsystem.demo.entity.College;
import com.educationsystem.demo.service.impl.CollegeServiceImpl;
import com.educationsystem.demo.tool.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    CollegeServiceImpl collegeService;

    /**
     * 查询全部学院数据
     * @return
     */
    @GetMapping("/selectALL")
    public Result selectALL(){
        log.info("查询全部学院数据");
//        orderByDesc倒叙查询
        List<College> collegeList=collegeService.list(new QueryWrapper<College>().orderByDesc("id"));
        return Result.sucess(collegeList);
    }

    /**
     * 通过学院名称查询学院信息
     * @param collegeName
     * @return
     */
//    @GetMapping("/selectByName/{name}")
//    public Result selectByName(@PathVariable String collegeName){
//        College college=collegeService.getByname(collegeName);
//        return Result.sucess(college);
//    }

    /**
     * 新增学院信息
     * @param college
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody College college) {
        if(StrUtil.isBlank(college.getName())){
            return Result.error("学院未知,插入失败！");
        }else {
            try {
//            mybatis-plus中封装的插入方法
                collegeService.save(college);
            } catch (Exception e) {
                log.error(String.valueOf(e));
                return Result.error("插入失败！");
            }
        }
        return Result.sucess();
    }

    /**
     * 更新学院信息
     * @param college
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody College college){
        if(StrUtil.isBlank(college.getName())||StrUtil.isBlank(college.getTeacher())){
            return Result.error("学院或者院系主任为空，更新失败！");
        }
        collegeService.updateById(college);
        return Result.sucess();
    }

    /**
     * 删除学院信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        collegeService.removeById(id);
        return Result.sucess();
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids){
        collegeService.removeByIds(ids);
        return Result.sucess();
    }

    /**
     * 多条件模糊查询
     * @param pageNum 当前页码
     * @param pageSize 每页数据个数
     * @param name 学院名称
     * @param teacher 院系主任名称
     * @return
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name,
                               @RequestParam String teacher){
        QueryWrapper<College> queryWrapper =new QueryWrapper<College>().orderByDesc("id");
        queryWrapper.like(StrUtil.isNotBlank(name),"name",name);
        queryWrapper.like(StrUtil.isNotBlank(teacher),"teacher",teacher);
        Page<College> page = collegeService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.sucess(page);
    }
}
