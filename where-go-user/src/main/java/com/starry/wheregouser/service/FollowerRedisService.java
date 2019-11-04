package com.starry.wheregouser.service;

import com.starry.wheregouser.bean.vo.FollowerVo;
import com.starry.wheregouser.bean.vo.UserVo;

import java.util.List;

/**
 * ClassName: FollowerRedisService
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 23:14
 **/
public interface FollowerRedisService {
    /**
     * 点赞。状态为1
     * @param userId
     * @param followerId
     */
    void saveLiked2Redis(String userId, String followerId);

    /**
     * 取消点赞。将状态改变为0
     */
    void unlikeFromRedis(String userId, String followerId);

    /**
     * 从Redis中删除一条点赞数据
     */
    void deleteLikedFromRedis(String userId, String followerId);

    /**
     * 该用户的点赞数加1
     */
    void incrementLikedCount(String userId);

    /**
     * 该用户的点赞数减1
     */
    void decrementLikedCount(String userId);

    /**
     * 获取Redis中存储的所有点赞数据
     * @return
     */
    List<FollowerVo> getLikedDataFromRedis();

    /**
     * 获取Redis中存储的所有点赞数量
     * @return
     */
    List<Object> getLikedCountFromRedis();
}
