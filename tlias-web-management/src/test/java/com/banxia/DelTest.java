package com.banxia;

import com.banxia.controller.DeleteController;
import com.banxia.utils.AliyunOSSOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DelTest {
    @Autowired
    AliyunOSSOperator aliyunOSSOperator;

    @Autowired
    DeleteController deleteController;
    @Test
    public void del() throws Exception {
        aliyunOSSOperator.delete("2025/06/c1558522-28c1-4060-a272-76936c4aa400.txt");
    }

    @Test
    public void add() throws Exception {
        deleteController.deleteByPath("2025/06/ecbae26b-2907-4cd1-8ead-985fda5731d8.png");

    }
}