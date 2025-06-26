package com.banxia.service.impl;

import com.banxia.annotation.Log;
import com.banxia.mapper.EmpExprMapper;
import com.banxia.mapper.EmpMapper;
import com.banxia.pojo.*;
import com.banxia.service.EmpLogService;
import com.banxia.service.EmpService;
import com.banxia.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 原始分页查询
//        // 调用mapper接口，查询总记录数
//        Long total = empMapper.count();
//        // 调用mapper接口，查询结果表
//        Integer start = (page-1)*pageSize;
//        List<Emp> list = empMapper.list(start, pageSize);

        // 设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        // 封装
        Page<Emp> p = (Page<Emp>) empList;
        // 返回
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class}) // 事务管理
    @Log
    @Override
    public void save(Emp emp) {
        try {
            // 基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.inster(emp);

            // 工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if(!(CollectionUtils.isEmpty(exprList))){
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insterBatch(exprList);
            }
        } finally {
            // 记录日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工" + emp);
            empLogService.insertLog(empLog);
        }


    }

    @Override
    @Log
    @Transactional(rollbackFor = {Exception.class})
    public void delete(List<Integer> ids) {
        // 基本信息
        empMapper.deleteByIds(ids);

        // 工作经历信息
        empExprMapper.deleteByEmpids(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 更新员工信息
     * @param emp
     */
    @Override
    @Log
    @Transactional(rollbackFor = {Exception.class})
    public void update(Emp emp) {
        System.out.println(emp);
        // 修改基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        // 修改工作经历信息
        // 删除 + 添加
        empExprMapper.deleteByEmpids(Arrays.asList(emp.getId()));
        if(!(CollectionUtils.isEmpty(emp.getExprList()))){
            empExprMapper.insterBatch(emp.getExprList());
        }
    }

    /**
     * 查找所有员工
     * @return
     */
    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    /**
     * 登录
     * @param emp
     * @return
     */
    @Override
    public LoginInfo login(Emp emp) {
//        调用mapper接口，查询用户信息
        Emp e  = empMapper.selsectByUsernameAndPassword(emp);
//        判断员工是否存在
        if(e != null){
            log.info("用户登录：" + e);
//            生成JWT令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }

//        不存在，返回null
        return null;
    }

    @Override
    public List<Emp> findByPosition(Integer jobId) {
        return empMapper.findByPosition(jobId);
    }
}
