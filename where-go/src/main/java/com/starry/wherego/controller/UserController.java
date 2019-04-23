package com.starry.wherego.controller;

import com.starry.wherego.bean.UserVo;
import com.starry.wherego.bean.dto.User;
import com.starry.wherego.bean.vo.CommonResult;
import com.starry.wherego.bean.vo.EnumCommonError;
import com.starry.wherego.exception.CommonException;
import com.starry.wherego.service.impl.UserServiceImpl;
import com.starry.wherego.util.MD5Util;
import com.starry.wherego.util.ShiroKit;
import org.apache.commons.mail.EmailException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
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
@RestController()
@RequestMapping("/common")
public class UserController {
    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public CommonResult register(@RequestParam("name") String name, @RequestParam("pwd") String pwd) throws CommonException {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString().replace("-",""));
        user.setTicket(ShiroKit.getRandomSalt(3));
        String md5 = ShiroKit.md5(pwd,user.getTicket());
        user.setUserName(name);
        user.setPwd(md5);
        UserVo vo = userService.register(user);
        if (vo == null) {
            throw new CommonException(EnumCommonError.USER_NOT_EXIXT);
        }
        return CommonResult.success(vo);
    }
    @PostMapping("/login")
    public CommonResult login(@RequestParam("name") String name,@RequestParam("pwd") String pwd) throws CommonException {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);
        try {
            subject.login(token);
            return CommonResult.success();
        } catch (UnknownAccountException e){
            return CommonResult.unauthorized(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return CommonResult.unauthorized(e.getMessage());
        }

    }
    @GetMapping("/getCode")
    public CommonResult getCode(@RequestParam("emailAddress") String telphone) throws EmailException {
        String code = userService.getCode(telphone);
        if (code == null) {
            new CommonException(EnumCommonError.USER_ALREADY_EXIST);
        }
        return CommonResult.success(code);

    }

}
