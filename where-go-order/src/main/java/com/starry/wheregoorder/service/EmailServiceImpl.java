package com.starry.wheregoorder.service;

import org.springframework.stereotype.Component;

/**
 * ClassName: EmailServiceImpl
 * Description: hystrix 熔断器接口实现
 *
 * @author: starryfei
 * @date: 2019-10-23 22:37
 **/
@Component
public class EmailServiceImpl implements EmailService {
    @Override
    public String sendOrder(String address, String content) {
        return "email server error";
    }
}
