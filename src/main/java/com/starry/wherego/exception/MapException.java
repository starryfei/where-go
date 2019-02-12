package com.starry.wherego.exception;

import java.net.ConnectException;
import java.net.SocketException;

/**
 * ClassName: MapException
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-02-12 16:58
 **/
public class MapException extends ConnectException {
    public MapException(String msg) {
        super(msg);
    }
    public MapException() {
    }

}
