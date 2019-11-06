package com.starry.common.vo.user;

import lombok.*;

import java.io.Serializable;

/**
 * ClassName: FollowerVo
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 23:13
 **/
@Setter
@Getter
@ToString
@Builder
//@NoArgsConstructor
public class FollowerVo implements Serializable {
    private String followerId;
    private String followerName;
    private String followerImage;

}