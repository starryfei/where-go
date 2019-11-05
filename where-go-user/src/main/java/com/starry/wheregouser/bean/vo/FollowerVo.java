package com.starry.wheregouser.bean.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName: FollowerVo
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 23:13
 **/
@Setter
@Getter
@Builder
@ToString
public class FollowerVo {
    private String followerName;
    private String followerImage;

}