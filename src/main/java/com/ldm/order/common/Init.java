package com.ldm.order.common;

import com.ldm.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Init {


    @Autowired
    public Init(UserService userService) {
        userService.createUser("jim", "123456");
        userService.createUser("tom", "654321");
    }
}
