package ru.top.java212.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


@SpringBootTest
@AutoConfigureMockMvc
public class StartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testing_the_initial_request() throws Exception{
    String url = "/";

    this.mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    void test_go_to_home_page_index() throws Exception{
        String url = "/";

        this.mockMvc.perform(get(url))
                .andExpect(content().string(containsString("index")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Home bookkeeping")));
    }


    @Test
    void test_go_to_page_menu() throws Exception{
        String url = "/menu";

        this.mockMvc.perform(get(url))
                .andExpect(model().size(1))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Family current balance")));
    }

    @Test
    void test_go_to_page_comeIn() throws Exception{
        String url = "/comeIn";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Authorization")));
    }
}

