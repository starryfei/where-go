package com.starry.wherego.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.starry.wherego.bean.Response;
import com.starry.wherego.common.MapCommon;
import com.starry.wherego.exception.MapException;
import com.starry.wherego.service.BaseService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: LocationServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-02-12 10:22
 **/
@Service
public class LocationServiceImpl implements BaseService {
    @Autowired
    private RestTemplate restTemplate;

    public Response getLocation(String ip) throws MapException {
        Map<String,String> parameters = new HashMap<>();
        parameters.put("ip",ip);
        parameters.put("ak",MapCommon.MAP_AK);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Response result = new Response();
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(MapCommon.IP_URL,HttpMethod.GET, entity,String.class, parameters);
            if(response.getStatusCode().is2xxSuccessful()) {
                result.setCode("200");
                JSONObject jsonObject = JSONObject.parseObject(response.getBody());
                result.setResult(jsonObject.toJSONString());
                System.out.println(result);
                return result;
            }
        } catch (Exception e){
            result.setCode("400");
            throw new MapException("Connection timed out");

        }
        return result;

    }
}
