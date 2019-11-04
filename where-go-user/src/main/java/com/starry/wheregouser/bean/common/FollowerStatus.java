package com.starry.wheregouser.bean.common;

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
    LIKE("1", "点赞"),
    /**
     *
     */
    UNLIKE("0", "取消点赞/未点赞"),
    ;

    private String code;

    private String msg;

    FollowerStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
