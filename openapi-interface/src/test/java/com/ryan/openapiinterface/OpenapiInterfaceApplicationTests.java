package com.ryan.openapiinterface;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.ryan.openapiinterface.mapper.UserMapper;
import com.ryan.openapiinterface.model.User;
import com.ryan.openapiinterface.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class OpenapiInterfaceApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> list = userService.list();
        System.out.println(list);
    }

}
