package com.starry.wheregouser.bean.common;

import io.swagger.models.auth.In;
import lombok.Getter;

/**
 * enum: FollowerStatus
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 23:22
 **/

@Getter
public enum FollowerStatus {
    /**
     *
     */
    LIKE(1, "关注"),
    /**
     *
     */
    UNLIKE(0, "取消关注"),
    ;

    private Integer code;

    private String msg;

    FollowerStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
