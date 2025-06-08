package com.banxia.controller;

import com.banxia.pojo.Dept;
import com.banxia.pojo.Result;
import com.banxia.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    // 查询部门列表
    @GetMapping
    public Result list(){
        log.info("查询全部的部门数据");
//        System.out.println("depts list");
        List<Dept> deptlist = deptService.findAll();
        return Result.success(deptlist);
    }

    // 根据id删除部门
    @DeleteMapping
    public Result deleteById(int id){
        log.info("根据ID删除部门：{}" ,id);
//        System.out.println("delete dept id "+id);
        deptService.deleteById(id);
        return Result.success();
    }

    // 添加部门
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}" ,dept);
        deptService.add(dept);
        return Result.success();
    }

    // 根据id查询部门
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据id查询部门数据：{}",id);
//        System.out.println("4find dept id "+id);
        Dept dept =  deptService.getById(id);
        return Result.success(dept);
    }

    // 根据id修改部门
    @PutMapping
    public Result updateById(@RequestBody Dept dept){
        log.info("根据id修改部门数据：{}",dept);
        System.out.println(dept);
        deptService.updateById(dept);
        return Result.success();
    }



    @RequestMapping(value = "/*")
    public Result aaa(){
        String str = "傻逼你发你妈呢";
        System.out.println(str);
        return Result.error(str);
    }


}
