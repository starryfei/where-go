package com.starry.wheregouser.service.impl;

import com.starry.wheregouser.bean.dto.Follower;
import com.starry.wheregouser.bean.vo.FollowerVo;
import com.starry.wheregouser.mapper.FollowerMapper;
import com.starry.wheregouser.service.FollowerRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class FollowerMapperTest {
    @Autowired
    FollowerMapper followerMapper;
    @Test

    public void addFollower(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = dateFormat.format(date);
        Follower follower = Follower.builder().userId("1").followerId("3").followerTime(string).status(1).build();
        followerMapper.saveFollower(follower);

        Follower follower1 = Follower.builder().userId("1").followerId("2").followerTime(string).status(1).build();

        followerMapper.saveFollower(follower1);

        Follower follower2 = Follower.builder().userId("1").followerId("4").followerTime(string).status(1).build();
        followerMapper.saveFollower(follower2);
    }
    @Test
    public void removeFollower(){
        followerMapper.unFollower("1","2");
    }

    @Test
    public void getAllFollowersCount(){
        int count = followerMapper.followerCount("1");
        log.info(count+" ");
    }
    @Test
    public void getAllFollowersD(){
        List<FollowerVo> count = followerMapper.followers("1");
        count.forEach(f ->log.info(f.toString()));
    }
}