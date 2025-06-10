package com.banxia.controller;

import com.banxia.annotation.Log;
import com.banxia.pojo.Result;
import com.banxia.service.DeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/delete")
public class DeleteController {

    @Autowired
    private DeleteService deleteService;
    /**
     * 根据路径删除文件
     * @param path 文件路径
     * @return 删除结果
     */
    @Log
    @DeleteMapping()
    public Result deleteByPath(String path) throws Exception {
        log.info("删除文件：{}", path);
        deleteService.deleteByPath(path);
        return Result.success();

    }

}
