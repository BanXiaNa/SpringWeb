package com.banxia.controller;

import com.banxia.pojo.Emp;
import com.banxia.pojo.EmpQueryParam;
import com.banxia.pojo.PageResult;
import com.banxia.pojo.Result;
import com.banxia.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     * @return
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name , Integer gender ,
//                       @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end) {
//
//        log.info("分页查询：{}，{}，{}，{}，{}，{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }

    /**
     * 员工列表查询
     * @param empQueryParam
     * @return
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {

        log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加员工
     * @param emp
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工：" + emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete (@RequestParam List<Integer> ids) {
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
        @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询信息：{}",id);
        Emp emp =  empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询全部员工
     * @return
     */
    @GetMapping("/list")
    public Result findAll() {
        log.info("查询所有员工信息。");
        List<Emp> list = empService.findAll();
        return Result.success(list);
    }

    /**
     * 根据职位查找员工
     * @author _ban_xia_
     */
    @GetMapping("/job")
    public Result findByPosition(Integer jobId) {
        log.info("根据职位查找员工：{}", jobId);
        List<Emp> list = empService.findByPosition(jobId);
        return Result.success(list);
    }
}
