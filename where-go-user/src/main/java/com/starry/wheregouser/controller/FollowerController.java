package com.starry.wheregouser.controller;

import com.starry.common.vo.CommonResult;
import com.starry.common.vo.user.FollowerVo;
import com.starry.context.UserApplicationContext;
import com.starry.wheregouser.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: FollowerController
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-05 23:37
 **/
@RestController
public class FollowerController {
    @Autowired
    FollowerService followerService;
    private UserApplicationContext context = UserApplicationContext.getInstance();

    @GetMapping("/follower")
    public CommonResult saveFollower(@RequestParam("userId") String userId){
        // followerId 是当前的用户的ID，不需要传入
        followerService.saveFollower(userId,context.getCurrentUser());
        return CommonResult.success("关注成功");

    }

    @GetMapping("/unfollower")
    public CommonResult unFollower(@RequestParam("userId") String userId){
        followerService.unFollower(userId,context.getCurrentUser());
        return CommonResult.success(" 取消关注成功");
    }

    @GetMapping("/followerCount")
    public CommonResult followerCount(){
        int count = followerService.followerCount(context.getCurrentUser());
        return CommonResult.success(count);
    }
    @GetMapping("/followerList")
    public CommonResult followers(){
        List<FollowerVo> followerVos = followerService.followers("1");
        return CommonResult.success(followerVos);

    }
}
