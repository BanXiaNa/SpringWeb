package com.banxia.service;

import com.banxia.pojo.Emp;
import com.banxia.pojo.EmpQueryParam;
import com.banxia.pojo.LoginInfo;
import com.banxia.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * 分页查询
     * @return
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 删除员工
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工
     * @param emp
     */
    void update(Emp emp);

    /**
     * 查询所有员工
     * @return
     */
    List<Emp> findAll();

    /**
     * 用户登录
     * @param emp
     * @return
     */
    LoginInfo login(Emp emp);
}
