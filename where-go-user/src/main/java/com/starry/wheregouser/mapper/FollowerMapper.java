package com.starry.wheregouser.mapper;

import com.starry.common.vo.user.FollowerVo;
import com.starry.wheregouser.bean.dto.Follower;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: FollowerMapper
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-05 22:56
 **/
public interface FollowerMapper {
    @Insert("insert into flow(user_id,flower_id,flower_time,status) " +
            "values (#{userId},#{followerId},#{followerTime},#{status})")
    void saveFollower(Follower follower);

    /**
     * 取消关注。将状态改变为0
     */
    @Update("update flow set status=0 where user_id=#{userId} and flower_id=#{followerId}")
    void unFollower(String userId, String followerId);

    /**
     * 粉丝数量
     */
    @Select("select count(flower_id) from flow where user_id=#{userId} and status=1")
    int followerCount(String userId);

    /**
     * 关注的人
     */
    @Select("select flow.flower_id, user.user_name,user.user_icon from user,flow " +
            "where flow.user_id =#{userId} and flow.flower_id=user.id")
    @Results(id="follower",value = {
            @Result(column="flower_id",property = "followerId"),
            @Result(column = "user_name",property = "followerName"),
            @Result(column = "user_icon",property = "followerImage")

    })
    List<FollowerVo> followers(String userId);
}
