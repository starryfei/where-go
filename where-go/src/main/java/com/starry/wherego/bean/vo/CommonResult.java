package com.starry.wherego.bean.vo;

import org.springframework.validation.BindingResult;

/**
 * ClassName: CommonResult
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-29 11:14
 **/
public class CommonResult {
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
    public static CommonResult result(Object data) {
        return result(SUCCESS,"success", data);
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
//    /**
//     * 返回分页成功数据
//     */
//    public CommonResult pageSuccess(List data) {
//        PageInfo pageInfo = new PageInfo(data);
//        Map<String, Object> result = new HashMap<>();
//        result.put("pageSize", pageInfo.getPageSize());
//        result.put("totalPage", pageInfo.getPages());
//        result.put("total", pageInfo.getTotal());
//        result.put("pageNum", pageInfo.getPageNum());
//        result.put("list", pageInfo.getList());
//        this.code = SUCCESS;
//        this.message = "操作成功";
//        this.data = result;
//        return this;
//    }

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
    public CommonResult unauthorized(String message) {
        this.code = UNAUTHORIZED;
        this.message = "暂未登录或token已经过期";
        this.data = message;
        return this;
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

//    @Override
//    public String toString() {
//        return JsonUtil.objectToJson(this);
//    }

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
