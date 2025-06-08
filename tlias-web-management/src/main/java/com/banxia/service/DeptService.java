package com.banxia.service;

import com.banxia.pojo.Dept;

import java.util.List;


public interface DeptService {

    /**
     * 查询所有的部门数据
     * @return
     */
    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept getById(Integer id);

    void updateById(Dept dept);
}
