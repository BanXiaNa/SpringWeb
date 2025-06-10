package com.banxia.service.impl;

import com.banxia.service.DeleteService;
import com.banxia.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteServiceImpl implements DeleteService {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @Override
    public void deleteByPath(String path) throws Exception {
        aliyunOSSOperator.delete(path);
    }
}
