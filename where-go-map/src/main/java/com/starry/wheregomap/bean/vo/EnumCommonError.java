package com.starry.wheregomap.bean.vo;

/**
 * enum: EnumCommonError
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-31 17:08
 **/
public enum EnumCommonError implements CommonError {
    /**
     * 未定义
     */
    UNDINFINE_ERROR(10000,"undifine error"),
    /**
     * 通用错误
     */
    COMON_ERROR(10001,""),
    /**
     * 参数校验失败
     */
    PARAMETER_VALIDATE_FAILED(10002,"parameters validate failure"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIXT(10003,"user not exist"),
    /**
     * 用户已经存在
     */
    USER_ALREADY_EXIST(10004,"user already exist"),
    /**
     * 用户没有权限
     */
    USET_NOT_FORBIDDEN(10005,"user not forbidden")
    ;

    private int code;
    private String errorMsg;

    EnumCommonError(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;

    }
    @Override
    public int getErrorCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.errorMsg;
    }

    @Override
    public CommonError setCommonError(String errorMsg) {
        return this;
    }}
