package com.starry.wherego.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.starry.wherego.bean.Response;
import com.starry.wherego.bean.dto.Location;
import com.starry.wherego.common.MapCommon;
import com.starry.wherego.dao.LocationDao;
import com.starry.wherego.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: LocationServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-02-12 10:22
 **/
@Service
public class LocationServiceImpl implements BaseService {

    private static Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LocationDao locationDao;
    static {

    }
    @Transactional()
    public Response getLocationByIp(String ip) {
//        redisTemplate.getExpire()
//        LOGGER.info(redisTemplate.opsForValue().get("hhh").toString());
        redisTemplate.opsForValue().set("ip", ip, 10, TimeUnit.SECONDS);
        String value = (String) redisTemplate.opsForValue().get("ip");
        LOGGER.info(value);
//        Long time = redisTemplate.getExpire("ip");
//        LOGGER.info("time:  "+time+"");

        Map<String,String> parameters = new HashMap<>(2);
        parameters.put("ip",ip);
        parameters.put("ak",MapCommon.MAP_AK);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Response result = new Response();
        ResponseEntity<String> response;

        response = restTemplate.exchange(MapCommon.IP_URL,HttpMethod.GET, entity,String.class, parameters);
        LOGGER.info(response.getBody());
        if(response.getStatusCode().is2xxSuccessful()) {
            result.setCode("200");
            JSONObject jsonObject = JSONObject.parseObject(response.getBody());
            JSONObject content = jsonObject.getJSONObject("content");
            JSONObject point = content.getJSONObject("point");
            result.setResult(jsonObject.toJSONString());
            result.setLng(point.get("x").toString());
            result.setLat(point.get("y").toString());
            result.setResult("success");
            Location location = new Location();
            location.setUserId(UUID.randomUUID().toString());
            location.setLat(result.getLat());
            location.setLng(result.getLng());
            locationDao.save(location);
            LOGGER.info(result.toString());

        } else {
            result.setCode("400");
            result.setResult("success");

        }
        return result;
    }

    public Response searchLocation(String address) {
        Map<String,String> parameters = new HashMap<>(2);
        parameters.put("address",address);
        parameters.put("ak",MapCommon.MAP_AK);
        Response result = new Response();
        ResponseEntity<String> response;
        response = restTemplate.getForEntity(MapCommon.ADDRESS_URL,String.class,parameters);
        return  requestBaidu(result, response);

    }

    public Response proprtAddress(String proAdderss,String city) {
        Map<String,String> parameters = new HashMap<>(4);
        parameters.put("ak",MapCommon.MAP_AK);
        parameters.put("region",city);
        parameters.put("query",proAdderss);
        Response result = new Response();
        ResponseEntity<String> response;
        response = restTemplate.getForEntity(MapCommon.POPMT_ABROAD,String.class,parameters);

        return requestBaidu(result,response);
    }


    private Response requestBaidu(Response result, ResponseEntity<String> response) {
        LOGGER.info(response.getBody());

        if(response.getStatusCode().is2xxSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(response.getBody());
            JSONObject res = jsonObject.getJSONObject("result");
            JSONObject location = res.getJSONObject("location");
            result.setLat(location.get("lat").toString());
            result.setLng(location.get("lng").toString());
            result.setCode("200");
            result.setResult("success");
        } else {
            result.setCode("400");
            result.setResult("failure");
        }
        return result;
    }
}
