package com.banxia.mapper;

import com.banxia.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    void insert(OperateLog log);

    //查询日志数据
    List<OperateLog> page();
}
