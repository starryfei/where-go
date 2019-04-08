package com.starry.wherego.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName: Response
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-02-12 10:55
 **/
@Setter
@Getter
@ToString
public class Response {
    private String code;
    private String result;
    private String lng;
    private String lat;


}
