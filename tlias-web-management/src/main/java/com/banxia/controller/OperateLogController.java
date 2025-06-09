package com.banxia.controller;

import com.banxia.pojo.OperateLog;
import com.banxia.pojo.PageResult;
import com.banxia.pojo.Result;
import com.banxia.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {

    @Autowired
    OperateLogService operateLogService;

    // 查询日志
    @GetMapping("/page")
    public Result  page(Integer  page, Integer pageSize) {
        log.info("查询日志 page: {}, pageSize: {}", page, pageSize);

        PageResult<OperateLog> pageResult = operateLogService.page(page, pageSize);

        return Result.success(pageResult);
    }
}
