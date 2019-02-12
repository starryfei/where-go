package com.starry.wherego.controlller;

import com.starry.wherego.bean.Response;
import com.starry.wherego.exception.MapException;
import com.starry.wherego.service.serviceImpl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: MapController
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-02-12 16:08
 **/
@RestController()
public class MapController {
    @Autowired
    private LocationServiceImpl locationService;
    @GetMapping("/lat")
    public Response latByIp(@RequestParam("ip") String ip) throws MapException {
        System.out.printf(ip);
        return locationService.getLocation(ip);
    }

}
