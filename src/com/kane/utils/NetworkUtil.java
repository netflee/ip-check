package com.kane.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copyright (C), 2018, Kane.Wang
 * FileName: NetworkUtil.java
 * 获取真实客户端IP地址工具类
 * @author Kane.Wang
 * @Date   2018-11-08
 * @version 1.00
 */
public final class NetworkUtil {

    private static Logger logger = Logger.getLogger("NetworkUtil");

    /**
     * 获取请求主机IP地址,如果使用代理则透过防火墙获取真实IP;
     *
     * @param request
     * @return ip
     * @throws IOException
     */
    public final static String getIpAddress(HttpServletRequest request) throws IOException {
        String ip = request.getHeader("X-Forwarded-For");
        logger.log(Level.INFO,"X-Forwarded-For:" + ip);

        if (ipIsUnknown(ip)) {
            if (ipIsUnknown(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                logger.log(Level.INFO,"Proxy-Client-IP:" + ip);
            }
            if (ipIsUnknown(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                logger.log(Level.INFO,"WL-Proxy-Client-IP:" + ip);
            }
            if (ipIsUnknown(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                logger.log(Level.INFO,"HTTP_CLIENT_IP:" + ip);

            }
            if (ipIsUnknown(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                logger.log(Level.INFO,"HTTP_X_FORWARDED_FOR:" + ip);
            }
            if (ipIsUnknown(ip)) {
                ip = request.getRemoteAddr();
                logger.log(Level.INFO,"getRemoteAddr:" + ip);
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 检查传入IP有效性
     * @param ip
     * @return
     */
    private final static boolean ipIsUnknown(String ip){
        boolean isUnknown = false;
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            isUnknown = true;
        }
        return isUnknown;
    }

}