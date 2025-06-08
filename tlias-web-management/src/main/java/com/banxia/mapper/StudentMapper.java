package com.banxia.mapper;

import com.banxia.pojo.Student;
import com.banxia.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

//    根据班级查看学生人数

    Integer findStudentCountByClazzId(Integer id);

//    分页查看学生信息
    List<Student> page(StudentQueryParam param);

//    添加学生
    void addStudent(Student student);

    Student findStudentById(int id);

    void updateStudent(Student student);

    // 删除学生
    void deleteStudentByIds(List<Integer> ids);

    void violation(Integer id, Integer score);

    @MapKey("clazzList")
    List<Map<Object, Object>> getstudentCountData();

    @MapKey("name")
    List<Map<Object, Object>> getStudentDegreeData();

}
