package com.starry.common.exception;

/**
 * ClassName: CommonException
 * Description: TODO
 * 包装器异常实现
 * @author: starryfei
 * @date: 2019-03-31 17:02
 **/
public class CommonException extends Exception implements CommonError {
    private CommonError commonError;

    public CommonException(CommonError commonError) {
        super();
        this.commonError = commonError;

    }
    public CommonException(CommonError commonError, String error) {
        super();
        this.commonError = commonError;
        this.commonError.setCommonError(error);

    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getMsg() {
        return this.commonError.getMsg();
    }

    @Override
    public CommonError setCommonError(String error) {
        this.commonError.setCommonError(error);
        return this.commonError;
    }
}
