package com.starry.wherego.config;

import com.starry.wherego.bean.dto.User;
import com.starry.wherego.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName: UserRelam
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-04-22 22:14
 **/
@Slf4j
public class UserRelam extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;
    /**
     * 授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserByName(token.getUsername());
        if(user == null) {
            throw new AccountException("用户不存在");
        }
        return new SimpleAuthenticationInfo(user,user.getPwd(), ByteSource.Util.bytes(user.getTicket()),getName());
    }
}
