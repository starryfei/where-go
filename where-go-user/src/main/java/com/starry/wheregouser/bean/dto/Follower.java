package com.starry.wheregouser.bean.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ClassName: Follower
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 23:10
 **/
@Setter
@Getter
@Builder
public class Follower {
    private int id;
    private String userId;
    private String followerId;
    private int status;
}
