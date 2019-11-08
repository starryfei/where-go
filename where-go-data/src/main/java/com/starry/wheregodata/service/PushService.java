package com.starry.wheregodata.service;

import com.starry.wheregodata.bean.vo.TravelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * ClassName: PushService
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-07 23:45
 **/
public interface PushService {

    /**
     * 推送用户数据被关注者的状态
     * @param userId
     * @return
     */
    List<TravelVo> pushExperience(String userName, String userId);






}
