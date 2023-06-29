package com.ryan.openapi;

import com.ryan.openapi.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@EnableDubbo
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class OpenapiGatewayApplication {

    @DubboReference
    private DemoService demoService;

    public static void main(String[] args) {
        ApplicationHome home = new ApplicationHome(OpenapiGatewayApplication.class);
        File jarFile = home.getSource();
        String dirPath = jarFile.getParentFile().toString();
        String filePath = dirPath + File.separator + ".dubbo";
        System.out.println(filePath);

        System.setProperty("dubbo.meta.cache.filePath", filePath);
        System.setProperty("dubbo.mapping.cache.filePath",filePath);

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
