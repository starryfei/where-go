package com.starry.wheregouser;

import com.starry.wheregouser.bean.dto.User;
import com.starry.wheregouser.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class WhereGoUserApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    public void selectUserByName(){
        User user = userMapper.selectUserByName("yafei");
        log.info(user.getUserName());
    }
    @Test
    public void reegister(){
        User user = userMapper.selectUserByName("yafei");
        log.info(user.getUserName());
    }

}
