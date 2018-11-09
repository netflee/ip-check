package com.kane.utils.test;

import com.kane.utils.ISPCheckUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ISPCheckUtilTest {

    private String testIp;
    private String testRes;

    @BeforeEach
    void setUp() {
        testIp = "125.70.0.141";
        testRes = "{\"as\":\"AS4134 No.31,Jin-rong Street\",\"city\":\"Chengdu\",\"country\":\"China\",\"countryCode\":\"CN\",\"isp\":\"China Telecom Sichuan\",\"lat\":30.6667,\"lon\":104.0667,\"org\":\"China Telecom Sichuan\",\"query\":\"125.70.0.141\",\"region\":\"SC\",\"regionName\":\"Sichuan\",\"status\":\"success\",\"timezone\":\"Asia/Shanghai\",\"zip\":\"\"}";
    }

    @AfterEach
    void tearDown() {
        testIp = null;
        testRes = null;
    }

    @Test
    void getISPInfo() throws IOException {

        assertEquals(testRes,ISPCheckUtil.getISPInfo(testIp));

    }
}