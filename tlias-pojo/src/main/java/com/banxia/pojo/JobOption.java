package com.banxia.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOption {
    // 职位列表
    private List<Object>jobList;
    // 数据列表
    private List<Object>dataList;
}
