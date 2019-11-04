package com.starry.wheregodata.bean.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName: TravelVo
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 22:31
 **/
@Setter
@Getter
@Builder
@ToString
public class TravelVo {
    private String docId;
    private String title;
    private String content;
    private String image;
    private String userName;
    private String label;
    private String location;
    private String createDate;
}
