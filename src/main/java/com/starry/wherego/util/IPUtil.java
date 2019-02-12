package com.starry.wherego.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * ClassName: IPUtil
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-02-12 10:24
 **/
public class IPUtil {
    public static void main(String[] arags) {
        getLocalIPv4Address();
    }
    public static InetAddress getLocalIPv4Address() {
        Enumeration<?> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        if (interfaces != null) {
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) interfaces.nextElement();
                Enumeration<?> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    System.out.println(ip.getHostAddress());
                    if (ip != null && (ip instanceof Inet4Address)) {
                        System.out.println("...."+ip.getHostAddress());
//                        if (!ip.getHostAddress().startsWith("127")) {
//                            return ip;
//                        }
                    }
                }
            }
        }
        return ip;
    }
}
