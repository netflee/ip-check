package com.kane.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copyright (C), 2018, Kane.Wang
 * FileName: ISPCheckUtil.java
 * IP详细信息查询工具类
 * @author Kane.Wang
 * @Date   2018-11-08
 * @version 1.00
 */
public final class ISPCheckUtil {
    private static Logger logger = Logger.getLogger("ISPCheckUtil");

    /**
     * 根据IP地址查询ISP相关信息
     *
     * @param ip
     * @return isp info
     * @throws IOException
     */
    public final static String getISPInfo(String ip) throws IOException {
        // 调用公网服务查询指定IP的ISP信息
        String url =  "http://ip-api.com/json/" + ip;
        logger.log(Level.INFO,"ISP Check URL:" + url);
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.connect();
        StringBuilder stringBuilder = new StringBuilder();
        //读取IP-API服务器的响应
        BufferedReader reader  = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        String line = null;
        while((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
        logger.log(Level.INFO,"ISP Info Result:" + stringBuilder.toString());
        return stringBuilder.toString();
    }

}
