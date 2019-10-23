package com.starry.wheregoorder.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * ClassName: EmailService
 * Description: FeignClient 本身实现了负载均衡RibbonClient
 *
 * @author: starryfei
 * @date: 2019-10-22 23:06
 **/
@FeignClient(value = "WHERE-GO-EMAIL", fallback = EmailServiceImpl.class )
public interface EmailService {

    @PostMapping("sendEmail")
    String  sendOrder(@RequestParam String address, @RequestParam String content);


}
