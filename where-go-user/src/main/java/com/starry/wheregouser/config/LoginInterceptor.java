package com.starry.wheregouser.config;

import com.alibaba.fastjson.JSON;
import com.starry.annontion.NoLogin;
import com.starry.common.exception.EnumCommonError;
import com.starry.wheregouser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * ClassName: LoginInterceptor
 * Description: 访问校验
 *
 * @author: starryfei
 * @date: 2019-10-28 21:54
 **/
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 如果标注@NoLogin 可以直接访问，不需要登陆
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.getAnnotation(NoLogin.class) != null) {
            return true;
        }
        //token验证
        String authStr = request.getHeader("authStr");
        log.info(authStr);
        String[] res = authStr.split(":");
        log.info(">>> "+userService.toString());
        boolean check = userService.checkLogin(res[0], authStr);
        if(check) {
            return true;
        }
        //验证未通过
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(EnumCommonError.USET_NOT_FORBIDDEN));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
