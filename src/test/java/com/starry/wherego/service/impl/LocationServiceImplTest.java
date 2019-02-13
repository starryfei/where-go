package com.starry.wherego.service.impl;

import com.starry.wherego.exception.MapException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration

public class LocationServiceImplTest {
    @Autowired
    private LocationServiceImpl locationService;
    @Test
    public void getLocation() throws MapException {
        String ip = "114.242.248.37";
        locationService.getLocationByIp(ip);
    }
    @Test
    public void searchAddress() throws MapException {
        String address = "北京";
        locationService.searchLocation(address);
    }

    @Test
    public void proprtAddress() throws MapException {
        String query = "天安门";
        String city = "北京";
        locationService.proprtAddress(query,city);
    }
}