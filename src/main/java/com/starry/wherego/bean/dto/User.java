package com.starry.wherego.bean.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * ClassName: User
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-28 19:16
 **/
@Entity
@Table(name="user_info")
@Setter
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String userName;
    private String pwd;
    private String ticket;
    private String telphone;
    private String email;

}
