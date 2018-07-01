/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.test.controller;

import com.w1s.api.test.common.TestBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by gukt(29283212@qq.com) on 2018/6/30 13:04
 *
 * @author gukt
 * @version 1.0
 */
@WebAppConfiguration
public class ParkingControllerTests extends TestBase {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .alwaysDo(MockMvcResultHandlers.print()).build();

        session = new MockHttpSession();
    }

    /**
     * 测试拉取停车场列表信息
     */
    @Test
    public void testParkingPots() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/parking/list")
                .param("longitude", "123.456")
                .param("latitude", "78.90");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    /**
     * 测试拉取停车场列表信息
     */
    @Test
    @Rollback(false)
    public void testParking() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/parking")
                .param("client_id", "aaa")
                .param("place_id", "1");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    /**
     * 测试拉取停车场列表信息
     */
    @Test
    public void testRequestPay() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/request_pay")
                .param("client_id", "aaa")
                .param("place_id", "1");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }
}
