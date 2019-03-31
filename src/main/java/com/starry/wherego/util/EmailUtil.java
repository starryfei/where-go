package com.starry.wherego.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * ClassName: EmailUtil
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-31 19:24
 **/
public class EmailUtil {
    public static void main(String[] args) throws EmailException {
        sendEmail("cccc","760646630fei@gmail.com");
    }
    public static void sendEmail(String code, String emailAddress) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.163.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("15611872648@163.com", "******"));
        email.setSSLOnConnect(true);
        email.setFrom("15611872648@163.com", "feiiiii");
        email.setSubject("验证码");
        email.setMsg("【where-you-go】验证码："+code+"，10分钟有效");
        email.addTo(emailAddress);
        String res = email.send();
        System.out.println(res);
    }
}
