package com.banxia.controller;

import com.banxia.pojo.PageResult;
import com.banxia.pojo.Result;
import com.banxia.pojo.Student;
import com.banxia.pojo.StudentQueryParam;
import com.banxia.service.StudentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    /**
     * 分页查询学生信息
     * @param param
     * @return
     */
    @GetMapping
    public Result page(StudentQueryParam param) {
        log.info("查询学生信息：" + param);
        return Result.success(studentsService.page(param));
    }

    /**
     * 添加学生
     * @param student
     * @return
     */
    @PostMapping
    public Result addStudent(@RequestBody Student student) {
        log.info("添加学生：" + student);
        studentsService.addStudent(student);
        return Result.success();
    }

    /**
     * 根据ID查询学生
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findStudentById(@PathVariable int id) {
        log.info("根据ID查询：" + id);

        Student student = studentsService.findStudentById(id);
        return Result.success(student);
    }


    /**
     * 更改学生信息
     * @param student
     * @return
     */
    @PutMapping
    public Result updateStudent(@RequestBody Student student) {
        log.info("学生信息更新：" + student);
        studentsService.updateStudent(student);
        return Result.success();
    }

    /**
     * 通过ID删除学生
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result deleteStudentByIds(@PathVariable List<Integer> ids) {
        log.info("删除学生：{}", ids);
        System.out.println("-----" + ids.toString() + "-----");

        if(ids.isEmpty()) {
            return Result.error("请确定要删除的学生~");
        }
        studentsService.deleteStudentByIds(ids);

        return Result.success();
    }

    /**
     * 违纪处理
     * @return
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation (@PathVariable Integer id,
                             @PathVariable Integer score) {
        log.info("记录一次违纪：{},{}",id,score);

        studentsService.violation(id,score);

        return Result.success();
    }
}
