package com.starry.wheregouser.controller;

import com.starry.annontion.NoLogin;
import com.starry.common.vo.CommonResult;
import com.starry.wheregouser.bean.dto.User;
import com.starry.wheregouser.bean.vo.UserVo;
import com.starry.wheregouser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserController
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-10-28 22:33
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @NoLogin
    public CommonResult login(@RequestParam("name") String name, @RequestParam("pwd") String pwd){
        UserVo vo = userService.login(name,pwd);
        return CommonResult.success(vo);
    }

    @PostMapping("/register")
    @NoLogin
    public CommonResult register(@RequestParam("name") String name,@RequestParam("pwd") String pwd,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone") String phone,@RequestParam("icon") String icon){
        User user = User.builder().userName(name).pwd(pwd).email(email).telphone(phone).icon(icon).build();
        UserVo vo = userService.register(user);
        return CommonResult.success(vo);

    }
    @GetMapping("/test")
    /**
     * 登陆测试
     */
    public CommonResult test(){
        return CommonResult.success();
    }

}
