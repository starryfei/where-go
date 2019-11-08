package com.starry.wheregodata.service.impl;

import com.alibaba.fastjson.JSON;
import com.starry.wheregodata.bean.vo.TravelVo;
import com.starry.wheregodata.service.PushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ClassName: PushServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-07 23:44
 **/
@Service
@Slf4j
public class PushServiceImpl implements PushService {
    @Autowired
    StringRedisTemplate redisTemplate;
    /**
     * 推送用户数据被关注者的状态
     *
     * @param userName
     * @param userId
     * @return
     */
    @Override
    public List<TravelVo> pushExperience(String userName, String userId) {
        String key = userName+"::"+userId;
        //redis 缓存读取关注的消息进行推送
        Set<String> results = redisTemplate.opsForZSet().range(key,0,-1);
        List<TravelVo> vos = new ArrayList<>();
        results.stream().map(JSON::parseObject).forEach(object -> {
            String content = object.getString("content");
            String title = object.getString("title");
            String docId = object.getString("docId");
            String image = object.getString("image");
            String name = object.getString("userName");
            String label = object.getString("label");
            String location = object.getString("location");
            String createDate = object.getString("createDate");
            TravelVo vo = TravelVo.builder().content(content)
                    .title(title).docId(docId)
                    .image(image).userName(name)
                    .label(label).location(location)
                    .createDate(createDate)
                    .build();
            log.info(vo.toString());
            vos.add(vo);
        });

        return vos;
    }
}
