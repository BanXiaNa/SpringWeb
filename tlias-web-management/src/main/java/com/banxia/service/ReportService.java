package com.banxia.service;

import com.banxia.pojo.JobOption;
import com.banxia.pojo.StudentCountDataOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ReportService {

    JobOption getEmpJobData();

    List<Map<Object,Object>> getEmpGenderData();

    StudentCountDataOption getStudentCountData();

    List<Map<Object, Object>> getStudentDegreeData();

}
