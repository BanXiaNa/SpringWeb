package com.banxia;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWarDeployment;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.Arrays;



@Slf4j
// 开启Springboot对Servlet组件的支持
@ServletComponentScan
@SpringBootApplication
public class TliasWebManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(TliasWebManagementApplication.class, args);

	}

}
