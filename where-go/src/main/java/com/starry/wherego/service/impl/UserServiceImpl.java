package com.starry.wherego.service.impl;

import com.starry.mq.EmailBean;
import com.starry.wherego.bean.UserVo;
import com.starry.wherego.bean.dto.User;
import com.starry.wherego.dao.UserDao;
import com.starry.wherego.service.BaseService;
import org.apache.commons.mail.EmailException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

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
        User check = getUserByName(user.getUserName());
        UserVo vo = new UserVo();
        if(check == null) {
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
    public User getUserByName(String name) {
        User user = userDao.findUserByUserName(name);

        return user;
    }

    public String getCode(String emailAddress) throws EmailException {
        User user = userDao.findUserByEmail(emailAddress);
        if(user == null) {
            Random random = new Random();
            int randonInt = random.nextInt(99999);
            randonInt += 10000;
            String code = String.valueOf(randonInt);
            stringRedisTemplate.opsForValue().set(emailAddress,code,10,TimeUnit.SECONDS);
            EmailBean mq = new EmailBean();
            mq.setEmailAddress(emailAddress);
            mq.setCode(code);
            rocketMQTemplate.asyncSend("email",mq, new SendCallback() {
                private SendResult sendResult;

                @Override
                public void onSuccess(SendResult sendResult) {
                    this.sendResult = sendResult;
                    LOGGER.info(sendResult.getSendStatus().toString());

                }
                @Override
                public void onException(Throwable throwable) {
                    LOGGER.info(sendResult.getSendStatus().toString());
                }
            });

            LOGGER.info(stringRedisTemplate.opsForValue().get(emailAddress));
            return code;
        }
        return null;
    }
}
