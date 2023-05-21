package com.ryan.openapiinterface;

import com.ryan.openapiclientsdk.client.OpenApiClient;
import com.ryan.openapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class OpenapiInterfaceApplicationTests {

    @Resource
    private OpenApiClient openApiClient;

    @Test
    void contextLoads() {
        String result = openApiClient.getNameByGet("ryan");
        User user = new User();
        user.setUsername("ryan");
        String result1 = openApiClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(result1);
    }

}
