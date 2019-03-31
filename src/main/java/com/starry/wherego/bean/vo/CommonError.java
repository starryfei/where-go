package com.starry.wherego.bean.vo;

/**
 * ClassName: CommonError
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-31 16:59
 **/
public interface CommonError {
    int getErrorCode();

    String getMsg();

    CommonError setCommonError(String error);

}
