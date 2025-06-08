package com.banxia.exception;


import com.banxia.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 唯一性报错
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("重复的手机号：",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        message = message.substring(i);
        String[] split = message.split(" ");
        return Result.error(split[2]+ "已存在");
    }

    @ExceptionHandler
    public Result handlerClazzHaveStudentException(ClazzHaveStudentException e) {
        log.error("删除的班级中有" + e.getMessage() + "人" );
        return Result.error("删除的班级中有" + e.getMessage() + "人");
    }

    /**
     * 捕获其他异常
     */
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错了：",e);
        return Result.error("服务器出现异常，请联系管理员~~~");
    }

}
