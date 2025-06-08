package com.banxia.controller;


import com.banxia.pojo.Emp;
import com.banxia.pojo.LoginInfo;
import com.banxia.pojo.Result;
import com.banxia.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("用户登录：" + emp);

        LoginInfo info = empService.login(emp);

        if(info == null){
            return Result.error("用户名或密码错误");
        }

        return Result.success(info);
    }
}
