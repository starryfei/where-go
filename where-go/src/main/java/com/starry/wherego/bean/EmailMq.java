package com.starry.wherego.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * ClassName: EmailMq
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-04-07 10:52
 **/
@Setter
@Getter
@ToString
public class EmailMq implements Serializable {
    private String email;
    private String code;
}
