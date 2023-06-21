package com.ryan.openapiinterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ryan.openapiinterface.mapper")
public class OpenapiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiInterfaceApplication.class, args);
    }

}
