package com.starry.wherego.service.impl;

import com.starry.wherego.bean.UserVo;
import com.starry.wherego.bean.dto.User;
import com.starry.wherego.dao.UserDao;
import com.starry.wherego.service.BaseService;
import com.starry.wherego.util.EmailUtil;
import com.starry.wherego.util.MD5Util;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-28 19:22
 **/
@Service
public class UserServiceImpl implements BaseService {
    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public UserVo login(String name,String pwd){
//        boolean md5 = MD5Util.checkMD5()
        UserVo vo = new UserVo();
        vo.setCode("200");
        vo.setResult("true");
        String cookie = stringRedisTemplate.opsForValue().get(name+pwd);
        LOGGER.info(cookie);
        if(cookie == null) {
            User user = userDao.findUserByUserNameAndPwd(name, pwd);

            if (user != null) {
                LOGGER.info(user.toString());
                vo.setUserImage(user.getUserName());
                stringRedisTemplate.opsForValue().set(name + pwd.toString(), user.getUserId(), 10, TimeUnit.SECONDS);
                return vo;
            }
            vo.setCode("222");
            vo.setResult("false");

            return vo;
        }
        return vo;
    }

    public UserVo register(User user) {
        boolean check = checkRegister(user);
        UserVo vo = new UserVo();
        if(check) {
            User reg = userDao.save(user);
            LOGGER.info(reg.toString());
            vo.setCode("200");
            vo.setResult("true");
            vo.setUserImage(reg.getUserName());
            return vo;
        } else {
            vo.setResult("false");
            vo.setCode("12");
        }

        return vo;
    }
    private boolean checkRegister(User user) {
        User u = userDao.findUserByUserName(user.getUserName());
        if (u != null) {
            return false;
        }
        return true;
    }

    public String getCode(String emailAddress) throws EmailException {
        User user = userDao.findUserByTelphone(emailAddress);
        if(user == null) {
            Random random = new Random();
            int randonInt = random.nextInt(99999);
            randonInt += 10000;
            String code = String.valueOf(randonInt);
            stringRedisTemplate.opsForValue().set(emailAddress,code,10,TimeUnit.SECONDS);
            EmailUtil.sendEmail(code,emailAddress);
            LOGGER.info(stringRedisTemplate.opsForValue().get(emailAddress));
            return code;
        }
        return null;
    }
}
