package com.starry.mq;

import java.io.Serializable;

/**
 * ClassName: com.starry.mq.EmailBean
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-04-07 18:01
 **/
public class EmailBean implements Serializable {
    private String emailAddress;
    private String code;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "com.starry.mq.EmailBean{" +
                "emailAddress='" + emailAddress + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
