package com.banxia.service.impl;

import com.banxia.annotation.Log;
import com.banxia.exception.ClazzHaveStudentException;
import com.banxia.mapper.ClazzsMapper;
import com.banxia.mapper.StudentMapper;
import com.banxia.pojo.*;
import com.banxia.service.ClazzsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzsServiceImpl implements ClazzsService {

    @Autowired
    private ClazzsMapper clazzsMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult page(ClazzsQueryParam clazzsQueryParam) {
        PageHelper.startPage(clazzsQueryParam.getPage(), clazzsQueryParam.getPageSize());
        List<Clazz> page = clazzsMapper.page(clazzsQueryParam);
        Page<Clazz> p = (Page<Clazz>) page;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    @Log
    @Override
    public void addClazzs(NewClazz newClazz) {
        newClazz.setCreateTime(LocalDateTime.now());
        newClazz.setUpdateTime(LocalDateTime.now());

        clazzsMapper.addClazzs(newClazz);
    }

    @Override
    public Clazz findClassById(Integer id) {
        return clazzsMapper.findClassById(id);
    }

    @Log
    @Override
    public void updateClass(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzsMapper.updateClass(clazz);
    }

    /**
     * 根据id删除班级
     * @param id
     */
    @Transactional
    @Log
    @Override
    public void deleteClassById(Integer id) throws ClazzHaveStudentException {
//        如果班级有人，就报错
        Integer studentCount = studentMapper.findStudentCountByClazzId(id);
        if(studentCount > 0) {
//            抛出有学生的自定义异常
            throw new ClazzHaveStudentException(studentCount.toString());
        }else {
            clazzsMapper.deleteClassById(id);
        }
    }

    @Override
    public List<Clazz> findAllClazzs() {
        return clazzsMapper.findAllClazzs();
    }
}
