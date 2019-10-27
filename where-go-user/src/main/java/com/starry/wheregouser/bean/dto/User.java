package com.starry.wheregouser.bean.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * ClassName: User
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-28 19:16
 **/
@Setter
@Getter
@Builder
@ToString
public class User {
    private Long id;
    private String userName;
    private String pwd;
//    private String ticket;
    private String telphone;
    private String email;
    private String icon;

}
