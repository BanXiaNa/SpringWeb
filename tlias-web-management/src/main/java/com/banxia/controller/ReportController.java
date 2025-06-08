package com.banxia.controller;


import com.banxia.pojo.JobOption;
import com.banxia.pojo.Result;
import com.banxia.pojo.StudentCountDataOption;
import com.banxia.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    /**
     * 统计员工职位人数
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数。");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别信息
     * @return
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderDada() {
        log.info("统计员工性别信息");
        List<Map<Object, Object>> empGenderDataList = reportService.getEmpGenderData();
        return Result.success(empGenderDataList);
    }

    /**
     * 统计班级人数信息
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        log.info("统计了班级信息。");

        StudentCountDataOption studentCountDataOption = reportService.getStudentCountData();

        return Result.success(studentCountDataOption);

    }

    /**
     * 统计学历分布
     * @return
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("查询学生的性别信息。");

        List<Map<Object,Object>>studentDegreeDataList = reportService.getStudentDegreeData();
        return Result.success(studentDegreeDataList);
    }
}
