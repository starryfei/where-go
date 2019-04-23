package com.starry.wherego.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: TimeHandlerInterceptor
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-04-12 10:17
 **/
@Slf4j
public class TimeHandlerInterceptor extends HandlerInterceptorAdapter {
    ThreadLocal<Long> timeLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、开始时间
        long beginTime = System.currentTimeMillis();
        //线程绑定变量（该数据只有当前请求的线程可见）
        timeLocal.set(beginTime);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //2、结束时间
        long endTime = System.currentTimeMillis();
        //得到线程绑定的局部变量（开始时间）
        long beginTime = timeLocal.get();

        //3、消耗的时间
        long consumeTime = endTime - beginTime;
        log.info("TIME:{}",consumeTime);
    }
}
