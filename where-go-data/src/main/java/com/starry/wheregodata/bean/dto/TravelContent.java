package com.starry.wheregodata.bean.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: TravelContent
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 21:44
 **/
@Setter
@Getter
@Builder
@ToString
public class TravelContent implements Serializable {
    private int id;
    private String docId;
    private String title;
    private String content;
    private String image;
    private String userId;
    private String userName;
    private String label;
    private String location;
    private String createDate;
    private int status;
}
