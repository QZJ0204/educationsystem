package com.educationsystem.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationsystem.demo.entity.Cource;
import com.educationsystem.demo.service.impl.CourceServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.educationsystem.demo.tool.Result;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cource")
public class CourceController {

    @Autowired
    CourceServiceimpl courceService;

    /**
     * 查找全部课程数据
     * @return
     */
    @GetMapping("/selectALL")
    public Result selectALL(){
        log.info("查询全部课程数据");
        List<Cource> CourceList=courceService.list(new QueryWrapper<Cource>().orderByDesc("id"));
        return Result.sucess(CourceList);
    }

    /**
     * 插入课程数据
     * @param cource
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Cource cource){
        if(StrUtil.isBlank(cource.getCName())|| StrUtil.isBlank(cource.getGrade())||StrUtil.isBlank(cource.getType())){
            return Result.error("缺少数据,插入失败！");
        }else {
            try {
//            mybatis-plus中封装的插入方法
                courceService.save(cource);
            } catch (Exception e) {
                return Result.error("插入失败！");
            }
        }
        return Result.sucess();
    }

    /**
     * 更新课程数据
     * @param cource
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Cource cource){
        if(StrUtil.isBlank(cource.getCName())|| StrUtil.isBlank(cource.getGrade())||StrUtil.isBlank(cource.getType())){
            return Result.error("缺少数据,更新失败！");
        }else {
            try {
//            mybatis-plus中封装的插入方法
                courceService.updateById(cource);
            } catch (Exception e) {
                return Result.error("更新失败！");
            }
        }
        return Result.sucess();
    }

    /**
     * 单个删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        courceService.removeById(id);
        return Result.sucess();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/delete/batch")
    public Result delbatch(@RequestBody List<Integer> ids){
        courceService.removeByIds(ids);
        return Result.sucess();
    }

    /**
     * 多条件模糊查询
     * @param pageNum 当前页面
     * @param pageSize 每页数据个数
     * @param cName 课程名称
     * @param grade 课程学分
     * @param type 课程类型
     * @return
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String cName,
                               @RequestParam String grade,
                               @RequestParam String type){
        QueryWrapper<Cource> queryWrapper =new QueryWrapper<Cource>().orderByDesc("id");
        queryWrapper.like(StrUtil.isNotBlank(cName),"c_name",cName);
        queryWrapper.like(StrUtil.isNotBlank(grade),"grade",grade);
        queryWrapper.like(StrUtil.isNotBlank(type),"type",type);
        Page<Cource> page = courceService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.sucess(page);
    }
}
