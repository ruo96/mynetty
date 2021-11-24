package com.wrh.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author wuruohong
 * @Classname FileReadControllerTest
 * @Description TODO
 * @Date 2021/11/24 17:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest/*(classes = MynettyApplication.class)*/  // 可以省略括号内的
@Slf4j
@AutoConfigureMockMvc  //-- 没效果
//@WebAppConfiguration
public class FileReadControllerTest{

    @Autowired
    private MockMvc mockMvc;

    //@Autowired
    //private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        System.out.println(" before setup");
        //mockMvc = MockMvcBuilders.standaloneSetup(new FileReadController()).build();
        //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void read() throws Exception {
        //mockMvc = MockMvcBuilders.standaloneSetup(new FileReadController()).build();
        System.out.println("mockMvc = " + mockMvc);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/file/read/stream")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println("contentAsString = " + contentAsString);
        Assert.assertNotNull(contentAsString);
    }
}