package com.banxia.service;

import com.banxia.pojo.OperateLog;
import com.banxia.pojo.PageResult;

public interface OperateLogService {


    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
