package com.banxia.service.impl;

import com.banxia.annotation.Log;
import com.banxia.mapper.StudentMapper;
import com.banxia.pojo.PageResult;
import com.banxia.pojo.Student;
import com.banxia.pojo.StudentQueryParam;
import com.banxia.service.StudentsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    StudentMapper studentMapper;

    /**
     * 分页查询学生信息
     * @param param
     * @return
     */
    @Override
    public PageResult page(StudentQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());

        List<Student> list = studentMapper.page(param);
        Page<Student> p = (Page<Student>)list;
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Log
    @Override
    public void addStudent(Student student) {
//        时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.addStudent(student);
    }

    @Override
    public Student findStudentById(int id) {
        return studentMapper.findStudentById(id);
    }

    @Log
    @Override
    public void updateStudent(Student student) {

        student.setUpdateTime(LocalDateTime.now());

        studentMapper.updateStudent(student);
    }

    @Log
    @Override
    public void deleteStudentByIds(List<Integer> ids) {
        studentMapper.deleteStudentByIds(ids);
    }

    @Log
    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id,score);
    }
}
