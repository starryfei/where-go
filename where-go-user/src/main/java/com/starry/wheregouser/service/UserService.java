package com.starry.wheregouser.service;

import com.starry.wheregouser.bean.dto.User;
import com.starry.wheregouser.bean.vo.UserVo;

/**
 * ClassName: UserService
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-10-28 21:51
 **/
public interface UserService {

    User selctUserByName(String name);

    UserVo login(String name, String pwd);

    boolean checkLogin(String name, String token);

    UserVo register(User user);

}
