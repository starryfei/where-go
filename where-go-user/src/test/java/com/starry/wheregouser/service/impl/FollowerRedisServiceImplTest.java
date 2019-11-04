package com.starry.wheregouser.service.impl;

import com.starry.wheregouser.bean.dto.User;
import com.starry.wheregouser.mapper.UserMapper;
import com.starry.wheregouser.service.FollowerRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class FollowerRedisServiceImplTest {
    @Autowired
    FollowerRedisService followerRedisService;
    @Test
    @Before
    public void insertRedis(){
        followerRedisService.saveLiked2Redis("1","2");
        followerRedisService.saveLiked2Redis("1","3");
        followerRedisService.saveLiked2Redis("1","4");
        followerRedisService.saveLiked2Redis("1","5");
    }
    @Test
    public void getRedis(){
        followerRedisService.unlikeFromRedis("1","2");
    }

    @Test
    @After
    public void getAllFollowers(){
        followerRedisService.getLikedDataFromRedis().forEach(res ->log.info(res.toString()));
    }
}