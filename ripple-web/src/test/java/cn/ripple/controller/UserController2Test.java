package cn.ripple.controller;

import cn.ripple.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Auther: Edwin
 * @Date: 2018/12/12 10:15
 * @Description:
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserController2Test {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;


    @Test
    public void listAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/emp"))
                .andExpect(status().isOk()) // 期待返回状态吗码200
                // JsonPath expression  https://github.com/jayway/JsonPath
                //.andExpect(jsonPath("$[1].name").exists()) // 这里是期待返回值是数组，并且第二个值的 name 存在，所以这里测试是失败的
                .andDo(print()); // 打印返回的 http response 信息
    }
}
