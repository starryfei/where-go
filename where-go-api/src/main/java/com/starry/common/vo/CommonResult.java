package com.starry.common.vo;

import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.io.Serializable;

/**
 * ClassName: CommonResult
 * Description: 响应信息包装
 *
 * @author: starryfei
 * @date: 2019-03-29 11:14
 **/
@NoArgsConstructor
public class CommonResult implements Serializable {
    //操作成功
    public static final int SUCCESS = 200;
    //操作失败
    public static final int FAILED = 500;
    //参数校验失败
    public static final int VALIDATE_FAILED = 404;
    //未认证
    public static final int UNAUTHORIZED = 401;
    //未授权
    public static final int  FORBIDDEN = 403;
    private int code;
    private String message;
    private Object data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public static CommonResult success(Object data) {
        return result(SUCCESS,"success", data);
    }
    public static CommonResult success() {
        return result(SUCCESS,"success",null);
    }

    /**
     *
     * @param code
     * @param data
     * @return
     */
    public static CommonResult result(int code, String message, Object data) {
        CommonResult result = new CommonResult();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 普通失败提示信息
     */
    public CommonResult failed() {
        this.code = FAILED;
        this.message = "操作失败";
        return this;
    }

    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public CommonResult validateFailed(String message) {
        this.code = VALIDATE_FAILED;
        this.message = message;
        return this;
    }

    /**
     * 未登录时使用
     *
     * @param message 错误信息
     */
    public static CommonResult unauthorized(String message) {
        CommonResult result = new CommonResult();
        result.setCode(UNAUTHORIZED);
        result.setMessage(message);
        return result;
    }

    /**
     * 未授权时使用
     *
     * @param message 错误信息
     */
    public CommonResult forbidden(String message) {
        this.code = FORBIDDEN;
        this.message = "没有相关权限";
        this.data = message;
        return this;
    }

    /**
     * 参数验证失败使用
     * @param result 错误信息
     */
    public CommonResult validateFailed(BindingResult result) {
        validateFailed(result.getFieldError().getDefaultMessage());
        return this;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
