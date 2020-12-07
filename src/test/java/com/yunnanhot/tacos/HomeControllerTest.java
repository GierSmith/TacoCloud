package com.yunnanhot.tacos;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


//主页控制器测试

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() {
        try {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("/"))
//                发起对“/”的get请求
                    .andExpect(MockMvcResultMatchers.status().isOk())
//                并且期望响应码为200
                    .andExpect(MockMvcResultMatchers.view().name("home"))
//                并且期望响应视图的名字是home
                    .andExpect(MockMvcResultMatchers.content()
                            .string(Matchers.containsString("Welcome to...")));
//                并且期望结果的内容包含Welcome to ...
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}
