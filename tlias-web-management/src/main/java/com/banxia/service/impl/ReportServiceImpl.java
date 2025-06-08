package com.banxia.service.impl;

import com.banxia.mapper.EmpMapper;
import com.banxia.mapper.StudentMapper;
import com.banxia.pojo.JobOption;
import com.banxia.pojo.Result;
import com.banxia.pojo.StudentCountDataOption;
import com.banxia.service.ReportService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<Object, Object>> list = empMapper.countEmpJobData();

        List<Object> posList = list.stream().map(datamap -> datamap.get("pos")).toList();
        List<Object> numList = list.stream().map(datamap -> datamap.get("num")).toList();

        return new JobOption(posList,numList);
    }

    @Override
    public List<Map<Object,Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public StudentCountDataOption getStudentCountData() {
        List<Map<Object,Object>> list = studentMapper.getstudentCountData();
        List<Object> clazzList = list.stream().map(datamap -> datamap.get("clazzList")).toList();
        List<Object> dataList = list.stream().map(datamap -> datamap.get("dataList")).toList();
        return new StudentCountDataOption(clazzList,dataList);
    }

    @Override
    public List<Map<Object, Object>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }
}
