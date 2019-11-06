package com.starry.wheregodata.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ShareExperienceServiceTest {
    @Autowired
    ShareExperienceService shareExperienceService;

    @Test
    public void getFollowerList(){
        shareExperienceService.getFollowerList();
    }

}