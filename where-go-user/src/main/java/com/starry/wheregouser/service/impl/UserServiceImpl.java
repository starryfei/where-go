package com.starry.wheregouser.service.impl;

import com.starry.wheregouser.bean.dto.User;
import com.starry.wheregouser.bean.vo.UserVo;
import com.starry.wheregouser.mapper.UserMapper;
import com.starry.wheregouser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-10-28 21:51
 **/
@Component
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;


    @Override
    public User selctUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public UserVo login(String name, String pwd) {
        User user = userMapper.login(name,pwd);
        if (user != null) {
            String token = generatorToken(user);
            log.info(user.getUserName(),token);
            return UserVo.builder().code("200").result("login success").token(token).build();
        } else {
            return UserVo.builder().code("100").result("login failure").build();
        }
    }

    @Override
    public boolean checkLogin(String name,String autr) {
        String token = redisTemplate.opsForValue().get(name);
        log.info(token,autr);
        if(autr.equals(token)) {
            // 获取token剩余时间，如果少于一个小时，重新增加一天的时间
            long time = redisTemplate.getExpire(name);
            if(time < 60 * 60 ) {
                log.info(name,time);
                redisTemplate.opsForValue().set(name,token,24*60*60,TimeUnit.SECONDS);
            }
            return true;
        }
        return false;
    }

    @Override
    public UserVo register(User user) {
        log.info(user.toString());
        User res = userMapper.selectUserByName(user.getUserName());
        if (res != null) {
            return UserVo.builder().code("100").result(res.getUserName()+" has already register").build();
        } else {
            userMapper.saveUser(user.getUserName(),user.getPwd(),user.getEmail(),user.getTelphone(),user.getIcon());
            String token = generatorToken(user);
            return UserVo.builder().code("200").result("register success").token(token).build();
        }
    }
    private String generatorToken(User user){
        String token = UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set(user.getUserName(),user.getUserName()+":"+token,24*60*60, TimeUnit.SECONDS);
        log.info(user.getUserName(),token);
        return user.getUserName()+":"+token;
    }
}
