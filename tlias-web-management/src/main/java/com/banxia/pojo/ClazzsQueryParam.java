package com.banxia.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 分页查询数据结构
 */
@Data
public class ClazzsQueryParam {
    private String name;//班级名称
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate begin;//开始时间
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate end;//结束时间
    private Integer page = 1;//分页查询页码
    private Integer pageSize = 10;//每页记录数
}
