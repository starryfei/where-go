package com.starry.wheregodata.service;

import com.starry.common.vo.user.FollowerVo;
import com.starry.wheregodata.service.impl.ShareExpreienceServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * ClassName: ShareExperienceService
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-06 22:21
 **/
@FeignClient(value = "WHERE-GO-USER", fallback = ShareExpreienceServiceImpl.class )
public interface ShareExperienceService {
    @GetMapping("followerList")
    String getFollowerList();

}
