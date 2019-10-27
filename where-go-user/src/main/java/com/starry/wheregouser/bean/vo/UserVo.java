package com.starry.wheregouser.bean.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName: UserVo
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-28 22:40
 **/
@Setter
@Getter
@Builder
@ToString
public class UserVo {
    private String result;
    private String code;

    private  String userImage;


}
