package com.starry.wheregouser.service.impl;

import com.starry.common.exception.CommonError;
import com.starry.common.exception.CommonException;
import com.starry.util.DateUtil;
import com.starry.wheregouser.bean.common.FollowerStatus;
import com.starry.wheregouser.bean.dto.Follower;
import com.starry.wheregouser.bean.vo.FollowerVo;
import com.starry.wheregouser.mapper.FollowerMapper;
import com.starry.wheregouser.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: FollowerServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-05 23:28
 **/
@Service
public class FollowerServiceImpl implements FollowerService {
    @Autowired
    FollowerMapper followerMapper;
    @Override
    public void saveFollower(String userId, String followerId) {
        Follower follower = Follower.builder()
                .userId(userId).followerId(followerId)
                .followerTime(DateUtil.getCurrentTime()).status(FollowerStatus.LIKE.getCode())
                .build();
        followerMapper.saveFollower(follower);
    }

    @Override
    @Transactional(rollbackFor = CommonException.class)
    public void unFollower(String userId, String followerId) {
        followerMapper.unFollower(userId, followerId);
    }

    @Override
    public int followerCount(String userId) {
        return followerMapper.followerCount(userId);
    }

    @Override
    public List<FollowerVo> followers(String userId) {
        return followerMapper.followers(userId);
    }

}
