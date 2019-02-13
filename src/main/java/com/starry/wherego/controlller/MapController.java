package com.starry.wherego.controlller;

import com.starry.wherego.bean.Response;
import com.starry.wherego.exception.MapException;
import com.starry.wherego.service.impl.LocationServiceImpl;
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
@RestController()
@RequestMapping("/api")
public class MapController {
    private static Logger LOGGER = LoggerFactory.getLogger(MapController.class);
    @Autowired
    private LocationServiceImpl locationService;


    @GetMapping("/ip")
    public Response latByIp(@RequestParam("ip") String ip) throws MapException {
        LOGGER.info(ip);
        return locationService.getLocationByIp(ip);
    }
    @GetMapping("/address")
    public Response latByAddress(@RequestParam("address") String address) throws MapException {
        LOGGER.info(address);
        return locationService.searchLocation(address);
    }

    @GetMapping("/suggestion")
    public Response suggestAddress(@RequestParam("query") String query,@RequestParam("city") String city)
            throws MapException {
        return locationService.proprtAddress(query,city);
    }

}
