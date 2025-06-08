package com.banxia.service;


import com.banxia.exception.ClazzHaveStudentException;
import com.banxia.pojo.Clazz;
import com.banxia.pojo.ClazzsQueryParam;
import com.banxia.pojo.NewClazz;
import com.banxia.pojo.PageResult;

import java.util.List;

public interface ClazzsService {

    PageResult page(ClazzsQueryParam clazzsQueryParam);

    void addClazzs(NewClazz newClazz);

    Clazz findClassById(Integer id);

    void updateClass(Clazz clazz);

    void deleteClassById(Integer id) throws ClazzHaveStudentException;

    List<Clazz> findAllClazzs();

}
