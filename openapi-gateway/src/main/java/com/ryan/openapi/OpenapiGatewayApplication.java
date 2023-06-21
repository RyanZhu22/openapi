package com.ryan.openapi;

import com.ryan.openapi.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
@EnableDubbo
@SpringBootApplication
public class OpenapiGatewayApplication {

    @DubboReference
    private DemoService demoService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OpenapiGatewayApplication.class, args);
        OpenapiGatewayApplication application = context.getBean(OpenapiGatewayApplication.class);
        String result = application.doSayHello("Hello");
        String result2 = application.doSayHello2("Hello");
        System.out.println("result: " + result);
        System.out.println("result: " + result2);
    }

    public String doSayHello2(String name) {
        return demoService.sayHello(name);
    }

    public String doSayHello(String name) {
        return demoService.sayHello2(name);
    }


}
