package com.starry.wheregodata.service.impl;

import com.starry.common.vo.user.FollowerVo;
import com.starry.wheregodata.service.ShareExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * ClassName: ShareExpreienceServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-06 22:22
 **/
@Component
@Slf4j
public class ShareExpreienceServiceImpl implements ShareExperienceService {
    @Override
    public String getFollowerList() {
        log.info("获取服务失败");
        return "error serivice failure";
    }
}
