package com.banxia.controller;


import com.banxia.pojo.Result;
import com.banxia.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@RestController()
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

//    @PostMapping("/upload")
//    public Result upload(String name,Integer age,MultipartFile file) throws IOException {
//        log.info("接收文件" + name + age + file);
//        //获取UUID
//        String originalFilename = UUID.randomUUID() + file.getOriginalFilename();
//        file.transferTo(new File("C:\\Users\\19432\\OneDrive\\notes\\JavaWeb\\SpringBoot_Web\\SpringWeb\\uploadFile\\" + originalFilename));
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}", file.getOriginalFilename());

        // 将文件交给OSS管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS，URL：{}",url);

        return Result.success(url);
    }
}
