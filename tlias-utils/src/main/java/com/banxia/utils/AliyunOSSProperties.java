package com.banxia.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    // 阿里云OSS的配置属性
    // Endpoint
    private String endpoint;
    // Bucket名称
    private String bucketName;
    // Bucket所在的区域
    private String region;
}
