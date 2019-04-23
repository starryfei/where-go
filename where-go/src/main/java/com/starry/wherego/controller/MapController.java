package com.starry.wherego.controller;

import com.starry.wherego.bean.Response;
import com.starry.wherego.service.impl.LocationServiceImpl;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: MapController
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-02-12 16:08
 **/
@Api("百度地图API接口")
@RestController()
@RequestMapping("/map")
public class MapController{
    private static Logger LOGGER = LoggerFactory.getLogger(MapController.class);
    @Autowired
    private LocationServiceImpl locationService;


    @ApiOperation("根据IP地址获取定位信息")
    @ApiImplicitParam(name = "ip", value = "IP地址", required = true, dataType = "String")
    @GetMapping("/ip")
    public Response latByIp(@RequestParam("ip") String ip) {
        LOGGER.info(ip);
        return locationService.getLocationByIp(ip);
    }
    @ApiOperation("根据地理位置获取定位信息")
    @ApiImplicitParam(name = "address", value = "地理位置", required = true, dataType = "String")
    @GetMapping("/address")
    public Response latByAddress(@RequestParam("address") String address) {
        LOGGER.info(address);
        return locationService.searchLocation(address);
    }
    @ApiOperation("获取周边信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "输入地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "city", value = "城市", required = true, dataType = "String")
    })

    @GetMapping("/suggestion")
    public Response suggestAddress(@RequestParam("query") String query,@RequestParam("city") String city) {
        return locationService.proprtAddress(query,city);
    }





}
