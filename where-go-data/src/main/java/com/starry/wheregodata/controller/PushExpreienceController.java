package com.starry.wheregodata.controller;

import com.starry.common.vo.CommonResult;
import com.starry.wheregodata.bean.vo.TravelVo;
import com.starry.wheregodata.service.PushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: PushExpreienceController
 * Description: 推送给用户的关注者发送的状态
 *
 * @author: starryfei
 * @date: 2019-11-07 23:44
 **/
@RestController
@Slf4j
public class PushExpreienceController {
    @Autowired
    PushService pushService;

    @GetMapping("/followerStatus")
    public CommonResult push() {
        String userName = "";
        String userId = "";

        List<TravelVo> vos = pushService.pushExperience(userName,userId);
        if(vos.isEmpty()) {
            return CommonResult.success("no travel message");
        }
        return CommonResult.success(vos);

    }
}
