package com.starry.wherego.email.controller;

import com.starry.wherego.email.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: EmailController
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-10-22 22:59
 **/
@RestController
@Slf4j
public class EmailController {

    @PostMapping("sendEmail")
    public String sendEmail(@RequestParam String address, @RequestParam String content) {
        try {
            log.info(address,content);
            EmailUtil.sendEmail(content, address);

        } catch (EmailException e) {
            log.error(e.getMessage(),address);
            return "400";
        }

        return "200";

    }
}
