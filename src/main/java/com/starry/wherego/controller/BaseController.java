package com.starry.wherego.controller;

import com.starry.wherego.bean.vo.CommonResult;
import com.starry.wherego.bean.vo.EnumCommonError;
import com.starry.wherego.exception.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: BaseController
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-31 17:46
 **/
public class BaseController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        Map<String,Object> responseMap = new HashMap<>();
        if (ex instanceof CommonException) {
            CommonException commonException = (CommonException) ex;
            responseMap.put("errorCode", commonException.getErrorCode());
            responseMap.put("errorMsg", commonException.getMsg());

        } else {
            responseMap.put("errorCode", EnumCommonError.UNDINFINE_ERROR.getErrorCode());
            responseMap.put("errorMsg", EnumCommonError.UNDINFINE_ERROR.getMsg());

        }
        return CommonResult.result(400, "failure", responseMap);

    }
}
