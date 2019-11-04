package com.starry.wheregouser.bean.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class FollowerVo {
    private String userId;
    private String followerId;
    private int status;
}