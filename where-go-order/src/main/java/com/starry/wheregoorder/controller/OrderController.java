package com.starry.wheregoorder.controller;

import com.starry.wheregoorder.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.cloud.client.ServiceInstance;

/**
 * ClassName: OrderController
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-10-22 23:17
 **/
@RestController
public class OrderController {
    @Autowired
    EmailService service;

    @GetMapping("/send")
    public String sendOrder(){
        String res  = service.sendOrder("aaa","dddd");
        return res;

    }
}
