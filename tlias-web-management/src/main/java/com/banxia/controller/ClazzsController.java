package com.banxia.controller;


import com.banxia.exception.ClazzHaveStudentException;
import com.banxia.pojo.*;
import com.banxia.service.ClazzsService;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzsController {

    @Autowired
    private ClazzsService clazzsService;

    /**
     * 分页查询班级
     * @param clazzsQueryParam
     * @return
     */
    @GetMapping
    public Result page(ClazzsQueryParam clazzsQueryParam){

        log.info("分页查询班级：" + clazzsQueryParam);
        PageResult page = clazzsService.page(clazzsQueryParam);

        return Result.success(page);
    }

    /**
     * 添加班级
     * @param newClazz
     * @return
     */
    @PostMapping
    public Result addClazzs(@RequestBody NewClazz newClazz){
        log.info("添加班级" + newClazz);

        clazzsService.addClazzs(newClazz);

        return Result.success();
    }

    /**
     * 根据ID查询班级信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findClassById(@PathVariable Integer id){
        log.info("根据id查询班级信息。");

        Clazz clazz = clazzsService.findClassById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     * @param clazz
     * @return
     */
    @PutMapping
    public Result updateClass(@RequestBody Clazz clazz){
        log.info("修改班级信息：" + clazz);
        clazzsService.updateClass(clazz);
        return Result.success();
    }

    /**
     * 根据id删除班级
     * @param id
     * @return
     * @throws ClazzHaveStudentException
     */
    @DeleteMapping("/{id}")
    public Result deleteClassById(@PathVariable Integer id) throws ClazzHaveStudentException {
        log.info("根据id删除班级：" + id);
        clazzsService.deleteClassById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result findAllClazzs(){
        log.info("查找了所有班级");
        List<Clazz> list = clazzsService.findAllClazzs();
        return Result.success(list);
    }
}

