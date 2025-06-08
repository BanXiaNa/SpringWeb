package com.banxia.service;


import com.banxia.pojo.PageResult;
import com.banxia.pojo.Student;
import com.banxia.pojo.StudentQueryParam;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentsService {

    /**
     * 分页查询学生信息
     * @param param
     * @return
     */
    PageResult page(StudentQueryParam param);


    /**
     * 添加学生
     * @param student
     */
    void addStudent(Student student);


    /**
     * 根据id查找学生
     * @param id
     * @return
     */
    Student findStudentById(int id);


    /**
     * 更新学生信息
     * @param student
     */
    void updateStudent(Student student);


    /**
     * 根据id批量删除学生信息
     * @param ids
     */
    void deleteStudentByIds(List<Integer> ids);

    /**
     * 违纪处理
     * @param id
     * @param score
     */
    void violation(Integer id, Integer score);
}
