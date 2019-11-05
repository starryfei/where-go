package com.starry.wheregouser.service;

import com.starry.wheregouser.bean.dto.Follower;
import com.starry.wheregouser.bean.vo.FollowerVo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ClassName: FollowerRedisService
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 23:14
 **/
public interface FollowerService {
    void saveFollower(String userId,String followerId);

    void unFollower(String userId, String followerId);

    int followerCount(String userId);

    List<FollowerVo> followers(String userId);
}
