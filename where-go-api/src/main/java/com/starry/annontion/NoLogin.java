package com.starry.annontion;

import java.lang.annotation.*;

/**
 * ClassName: NoLogin
 * Description: 不需要登陆就可以访问的方法
 *
 * @author: starryfei
 * @date: 2019-10-28 21:55
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoLogin {
}
