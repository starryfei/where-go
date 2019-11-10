package com.starry.wheregouser.service.impl;

import com.starry.wheregouser.bean.dto.User;
import com.starry.wheregouser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserServiceImplTest {
    @Autowired
    UserService userService;
    @Test
    public void register(){
        User user = User.builder().userName("ccc").pwd("123").email("1111").telphone("1111").icon("d").build();
        userService.register(user);
    }
    @Test
    public void login(){
        userService.login("ccc","123");
        log.info("---------------");
        userService.login("ccc","113");
    }

}