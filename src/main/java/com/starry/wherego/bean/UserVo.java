package com.starry.wherego.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.Cookie;

/**
 * ClassName: UserVo
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-28 22:40
 **/
@Setter
@Getter
@ToString
public class UserVo {
    private String result;
//    private Cookie cookie;
    private String code;

    private  String userImage;


}
