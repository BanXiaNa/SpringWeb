package com.banxia.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page = 1;//页码
    private Integer pageSize = 10;//每页记录数
    private String name;//姓名
    private Integer gender;//性别
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate begin;//入职时间-开始
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate end;//入职时间-结束
}
