package com.banxia.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 新建班级的数据类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewClazz {
    private String name;//班级名称
    private String room;//教室
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate beginDate;//开班时间
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate endDate;//结班时间
    private Integer masterId;//教师ID
    private Integer subject;//学科
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
