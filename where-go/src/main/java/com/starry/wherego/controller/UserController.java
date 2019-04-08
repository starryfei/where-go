package com.starry.wherego.controller;

import com.starry.wherego.bean.UserVo;
import com.starry.wherego.bean.dto.User;
import com.starry.wherego.bean.vo.CommonResult;
import com.starry.wherego.bean.vo.EnumCommonError;
import com.starry.wherego.exception.CommonException;
import com.starry.wherego.service.impl.UserServiceImpl;
import com.starry.wherego.util.MD5Util;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: UserController
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-28 22:36
 **/
@RestController
public class UserController {
    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/register")
    public CommonResult register(@RequestParam("name") String name, @RequestParam("pwd") String pwd) throws CommonException {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString().replace("-",""));
        String md5 = MD5Util.getMD5(pwd);
        user.setUserName(name);
        user.setPwd(md5);
        UserVo vo = userService.register(user);
        if (vo == null) {
            throw new CommonException(EnumCommonError.USER_NOT_EXIXT);
        }
        return CommonResult.result(vo);
    }
    @PostMapping("/login")
    public CommonResult login(@RequestParam("name") String name,@RequestParam("pwd") String pwd) throws CommonException {

        UserVo vo = userService.login(name,pwd);
        if(vo == null) {
            throw new CommonException((EnumCommonError.USER_NOT_EXIXT));
        }
        Cookie cookie = new Cookie("cookie",vo.getCode());
        LOGGER.info(cookie.toString());
        return CommonResult.result(vo);
    }
    @GetMapping("/getCode")
    public CommonResult getCode(@RequestParam("emailAddress") String telphone) throws EmailException {
        String code = userService.getCode(telphone);
        if (code == null) {
            new CommonException(EnumCommonError.USER_ALREADY_EXIST);
        }
        return CommonResult.result(code);

    }
}
