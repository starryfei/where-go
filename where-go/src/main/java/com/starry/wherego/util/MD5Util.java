package com.starry.wherego.util;

import org.springframework.util.DigestUtils;

/**
 * ClassName: MD5Util
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-29 11:07
 **/
public class MD5Util {
    public static String getMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static boolean checkMD5(String original, String md5) {
        return getMD5(original).equals(md5);
    }

}
