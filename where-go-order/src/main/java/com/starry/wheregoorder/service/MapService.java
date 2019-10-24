package com.starry.wheregoorder.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: MapService
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-10-24 23:21
 **
*/

@FeignClient(value = "WHERE-GO-MAP")
public interface MapService {

    @GetMapping("/map/address")
    Object latByAddress(@RequestParam("address") String address);

}
