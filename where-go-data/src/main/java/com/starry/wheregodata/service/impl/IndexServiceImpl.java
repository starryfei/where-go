package com.starry.wheregodata.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.starry.common.vo.user.FollowerVo;
import com.starry.wheregodata.bean.dto.TravelContent;
import com.starry.wheregodata.bean.vo.TravelVo;
import com.starry.wheregodata.mapper.IndexMapper;
import com.starry.wheregodata.service.IndexService;
import com.starry.wheregodata.service.ShareExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: IndexServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 22:40
 **/
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {
    @Autowired
    IndexMapper indexMapper;

    @Autowired
    ShareExperienceService shareExperienceService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<TravelVo> index(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<TravelContent> results = indexMapper.index();
        log.info("total value: "+((Page)results).getTotal());
        return converToVo(results);
    }

    @Override
    public List<TravelVo> allData() {

        List<TravelContent> results = indexMapper.allData();

        return converToVo(results);
    }


    @Override
    public void insertData(TravelContent content) {
        // 存储到数据库中
        indexMapper.insertData(content);
        // 放到redis中，用于用户登陆进行文章推送
        String followerVos = shareExperienceService.getFollowerList();
        log.info(followerVos);

        if(!followerVos.startsWith("error")) {
            JSONObject object = JSON.parseObject(followerVos);
            JSONArray array = (JSONArray) object.get("data");
            for(int i=0 ;i<array.size();i++){
               JSONObject vo = array.getJSONObject(i);
               String name = vo.getString("followerName");
               String followId = vo.getString("followerId");
                String userKey = name+"::"+followId;

                log.info(userKey);
                String value = JSON.toJSONString(content);
                stringRedisTemplate.opsForZSet().add(userKey,value, System.currentTimeMillis());

            }
//            JsonObject result = JSON.parseObject(followerVos,JsonObject.class);
//            log.info(result.toString());
//            List<Object> followerVoList = (List<Object>) c.getData();
//            followerVoList.forEach(followerVo -> {
//                log.info(followerVo.getClass().getName());
//
//                String userKey = followerVo.getFollowerId()+"::"+followerVo.getFollowerName();
//                String value = JSON.toJSONString(content);
//                stringRedisTemplate.opsForZSet().add(userKey,value, System.currentTimeMillis());
//            });

        }


    }

    @Override
    public void deleteData(String userId, String docId) {
        indexMapper.deleteData(userId,docId);
    }

    @Override
    public List<TravelVo> getDataByUserId(String userId) {
        return null;
    }

    private List<TravelVo> converToVo(List<TravelContent> results) {
        List<TravelVo> vos = new ArrayList<>(results.size());
        results.forEach(res -> {
            TravelVo vo = TravelVo.builder().build();
            BeanUtils.copyProperties(res,vo);
            vos.add(vo);
        });
        return vos;
    }
}
