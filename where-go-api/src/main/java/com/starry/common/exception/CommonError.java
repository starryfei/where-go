package com.starry.common.exception;

/**
 * ClassName: CommonError
 * Description: 异常通用接口
 *
 * @author: starryfei
 * @date: 2019-03-31 16:59
 **/
public interface CommonError {
    int getErrorCode();

    String getMsg();

    CommonError setCommonError(String error);

}
