package com.starry.wheregouser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * ClassName: WebConfig
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-04-12 10:23
 **/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/*/**");


    }

    /**
     * Override this method to configure cross origin requests processing.
     *
     * @param registry
     * @see CorsRegistry
     * @since 4.2
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        // 跨域
        registry.addMapping("/**");
    }

    /**
     * Override this method to add resource handlers for serving static resources.
     *
     * @param registry
     * @see ResourceHandlerRegistry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
