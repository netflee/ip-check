package com.kane.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kane.utils.ISPCheckUtil;
import com.kane.utils.NetworkUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Copyright (C), 2018, Kane.Wang
 * FileName: IpDetectsHandler.java
 * IP检测Servlet，提供REST接口，返回IP相关信息JSON报文
 * @author Kane.Wang
 * @Date   2018-11-08
 * @version 1.00
 */

public class IpDetectsHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger("IpDetectsHandler");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = NetworkUtil.getIpAddress(request);
        String ispInfo = ISPCheckUtil.getISPInfo(ip);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();

        //把数据解析为JSON
        JSONObject ispInfoJson = JSON.parseObject(ispInfo);
        logger.log(Level.INFO,"ISP JSON INFO =" + JSON.toJSONString(ispInfoJson,true));

        writer.write(JSON.toJSONString(ispInfoJson,true));
        writer.flush();
        writer.close();
    }

}