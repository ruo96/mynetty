package com.wrh.controller;

import com.wrh.MynettyApplication;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wuruohong
 * @Classname ApiControllerTest
 * @Description TODO
 * @Date 2021/1/6 16:36
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() throws Exception {

        System.out.println("--------------start----------------");
        mockMvc = MockMvcBuilders.standaloneSetup(new ApiController()).build();
//        api();
        System.out.println("--------------end----------------");
    }

    @Test
    void api() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new ApiController()).build();
        System.out.println(mockMvc);
        ResultActions resultActions =
        mockMvc.perform(MockMvcRequestBuilders.get("/api")
                .param("username", "wrh")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(System.out::print);

    }

    @Test
    @Transactional
    @Rollback()
    public void save() throws Exception {
        /*String json"{……}";
        //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
        mockMvc.perform(MockMvcRequestBuilders
                .post("/XXX/save")
                .content(json.getBytes()) //传json参数
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization","Bearer ********-****-****-****-************")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());*/
    }

    @Test
    public void get() throws Exception{
        /*ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/XXX/get")
                .param("id", "**********")
                .header("Authorization", "Bearer ********-****-****-****-************")
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());*/

    }
}