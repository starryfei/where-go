package com.starry.wheregodata.service.impl;

import com.starry.wheregodata.service.PushService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PushServiceImplTest {
    @Autowired
    PushService pushService;

    @Test
    public void pushMessage(){
        pushService.pushExperience("yafei1","2");
    }

}