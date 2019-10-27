package com.starry.wheregouser.exception;

import com.starry.common.exception.CommonException;
import com.starry.common.exception.EnumCommonError;
import com.starry.common.vo.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: BaseController
 * Description: 全局异常处理
 *
 * @author: starryfei
 * @date: 2019-03-31 17:46
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        Map<String,Object> responseMap = new HashMap<>();
        System.out.println(ex);
        if (ex instanceof CommonException ) {
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
