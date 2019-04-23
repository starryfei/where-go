package com.starry.wherego.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

/**
 * ClassName: ShiroKit
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-04-23 19:46
 **/
public class ShiroKit {

    /**
     * 加盐参数
     */
    public final static String hashAlgorithmName = "md5";

    /**
     * 循环次数
     */
    public final static int hashIterations = 2;

    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param saltSource  密码盐
     * @return
     */
    public static String md5(String credentials, String saltSource) {
        ByteSource salt = ByteSource.Util.bytes(saltSource);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
    }

    /**
     * 获取随机盐值
     *
     * @param length
     * @return
     */
    public static String getRandomSalt(int length) {
        return UUID.randomUUID().toString().substring(0,length);
    }

}
