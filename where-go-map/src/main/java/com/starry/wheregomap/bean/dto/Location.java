package com.starry.wheregomap.bean.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: Location
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-27 23:20
 **/
//@Entity
@Getter
@Setter
//@Table(name = "location")
public class Location {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)

    private long id;
    private String userId;
    private String lng;
    private String lat;
}
