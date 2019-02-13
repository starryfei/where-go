package com.starry.wherego.common;

/**
 * ClassName: MapCommon
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-02-12 10:03
 **/
public class MapCommon {
    public final static String MAP_AK = "6q11hNHkI0Ar8Um6Wtrn1GaICwz8tGw0";

    public final static String ADDRESS_URL = "http://api.map.baidu.com/geocoder/v2/?address={address}&output=json" +
            "&ak={ak}&callback=showLocation";

    public final static String IP_URL = "http://api.map.baidu.com/location/ip?ip={ip}&ak={ak}&coor=bd09ll";


    public final static String GPS_URL = "http://api.map.baidu.com/geoconv/v1/?coords={coords}&from=1&to=5&ak=你的密钥 //GET请求";

    public final static String ADDRESS_ABROAD = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=39.97733627005336,116.34602656927882&output=json&pois=1&&ak=6q11hNHkI0Ar8Um6Wtrn1GaICwz8tGw0";
    public final static String PLACE_ABROAD = "http://api.map.baidu.com/place/v2/search?query={query}&region=北京&output=json&ak={ak}";
    public final static String POPMT_ABROAD = "http://api.map.baidu.com/place/v2/suggestion?query={query}&" +
            "region={region}&city_limit=true&output=json&ak={ak}";



}
