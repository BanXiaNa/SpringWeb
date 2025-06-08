package com.banxia.mapper;

import com.banxia.pojo.Clazz;
import com.banxia.pojo.ClazzsQueryParam;
import com.banxia.pojo.NewClazz;
import com.banxia.pojo.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Mapper
public interface ClazzsMapper {
    List<Clazz> page(ClazzsQueryParam clazzsQueryParam);

    void addClazzs(NewClazz newClazz);

    Clazz findClassById(Integer id);

    void updateClass(Clazz clazz);

    void deleteClassById(Integer id);

    List<Clazz> findAllClazzs();

}
