package com.starry.wheregouser.service.impl;

import com.starry.wheregouser.bean.common.FollowerStatus;
import com.starry.wheregouser.bean.dto.Follower;
import com.starry.wheregouser.bean.vo.FollowerVo;
import com.starry.wheregouser.bean.vo.UserVo;
import com.starry.wheregouser.service.FollowerRedisService;
import com.starry.wheregouser.util.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: FollowerRedisServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 23:16
 **/
@Service
@Slf4j
public class FollowerRedisServiceImpl implements FollowerRedisService {
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 点赞。状态为1
     *
     * @param userId
     * @param followerId
     */
    @Override
    public void saveFollow2Redis(String userId, String followerId) {
        String key = RedisKeyUtils.getLikedKey(userId,followerId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED,key, FollowerStatus.LIKE.getCode().toString());
    }

    /**
     * 取消点赞。将状态改变为0
     *
     * @param userId
     * @param followerId
     */
    @Override
    public void unFollow2Redis(String userId, String followerId) {
        String key = RedisKeyUtils.getLikedKey(userId,followerId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED,key, FollowerStatus.UNLIKE.getCode().toString());
    }

    /**
     * 从Redis中删除一条点赞数据
     *
     * @param userId
     * @param followerId
     */
    @Override
    public void deleteFollower2Redis(String userId, String followerId) {
        String key = RedisKeyUtils.getLikedKey(userId,followerId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED,key);
    }

    /**
     * 该用户的点赞数加1
     *
     * @param userId
     */
    @Override
    public void incrementLikedCount(String userId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, userId, 1);

    }

    /**
     * 该用户的点赞数减1
     *
     * @param userId
     */
    @Override
    public void decrementLikedCount(String userId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, userId, -1);


    }

    /**
     * 获取Redis中存储的所有点赞数据
     *
     * @return
     */
    @Override
    public List<Follower> getLikedDataFromRedis() {
       Cursor<Map.Entry<Object, Object>> res = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED,
               ScanOptions.scanOptions().build());
       List<Follower> vos = new ArrayList<>();
       res.forEachRemaining((entry) ->{
           String key = (String) entry.getKey();
           log.info(key, entry.getValue());
           String[] ids = RedisKeyUtils.spiltRedisKey(key);
           String userId = ids[0];
           String followerId = ids[1];
           String status = (String) entry.getValue();
           Follower vo = Follower.builder()
                   .followerId(followerId).userId(userId)
                   .status(Integer.valueOf(status))
                   .build();
           vos.add(vo);
       });

       return vos;
    }

    /**
     * 获取Redis中存储的所有点赞数量
     *
     * @return
     */
    @Override
    public List<Object> getLikedCountFromRedis() {
        return null;
    }
}
