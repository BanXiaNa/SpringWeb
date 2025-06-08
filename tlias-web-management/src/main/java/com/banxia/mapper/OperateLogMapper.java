package com.banxia.mapper;

import com.banxia.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    void insert(OperateLog log);

}
