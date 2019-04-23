package com.starry.wherego.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: EncodeFilter
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-04-23 15:09
 **/
@Component
public class EncodeFilter  implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 设置编码为UTF-8
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        //过滤结束，继续执行    没有这一行，程序不会继续向下执行
        chain.doFilter(req, res);

    }

    @Override
    public void destroy() {
    }
}
