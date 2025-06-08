package com.banxia;

import com.banxia.mapper.EmpMapper;
import com.banxia.service.ClazzsService;
import com.example.TokenParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class TliasWebManagementApplicationTests {


	@Autowired
	private TokenParser tokenParser;

	@Test
	 void contextLoads() {
		tokenParser.parse();
	}


}
