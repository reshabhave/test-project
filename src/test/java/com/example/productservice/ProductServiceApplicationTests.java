package com.example.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void mainMethodStartsApplication() {
        ProductServiceApplication.main(new String[]{
                "--server.port=0",
                "--spring.main.web-application-type=none"
        });
    }
}
