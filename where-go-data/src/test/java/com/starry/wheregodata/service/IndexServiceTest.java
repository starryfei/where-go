package com.starry.wheregodata.service;

import com.starry.wheregodata.bean.dto.TravelContent;
import com.starry.wheregodata.mapper.IndexMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class IndexServiceTest {
    @Autowired
    IndexService indexService;
    @Test
    public void index(){
        indexService.index(1,2).forEach(res ->log.info(res.toString()));
    }

    @Test
    public void insertData(){
        TravelContent content = TravelContent.builder().docId(UUID.randomUUID().toString().substring(0,10))
                .title("hello world")
                .content("yafeiiiiiiiiiiiiiii")
                .image("mmmmmmmm")
                .userId("1")
                .userName("yafei")
                .createDate("..................")
                .label("aaa")
                .location("beijing")
                .status(1).build();
        indexService.insertData(content);
    }

}
