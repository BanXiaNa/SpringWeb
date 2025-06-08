package com.banxia.mapper;

import com.banxia.pojo.Emp;
import com.banxia.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

//    原始分页查询
//    /**
//     * 查询总记录数
//     * @return 查询到的记录数
//     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();
//
//    /**
//     * 分页查询
//     * @param start 开始页码
//     * @param pageSize 分页大小
//     * @return 包含用户信息类的列表
//     */
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp>list(Integer start, Integer pageSize);
    
    
//    // PageHelper 查询
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc ")
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     * @param emp
     */
    void inster(Emp emp);

    /**
     * 根据id批量删除员工基本信息
     * @param ids
     */
    void deleteByIds(List<Integer> ids);


    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据id更新用户数据
     * @param emp
     */
    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<Object,Object>>countEmpJobData();

    @MapKey("name")
    List<Map<Object, Object>> countEmpGenderData();

    List<Emp> findAll();

    Emp selsectByUsernameAndPassword(Emp emp);
}
