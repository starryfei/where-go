package com.starry.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: DateUtil
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-05 23:31
 **/
public class DateUtil {
    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        return time;
    }
}
