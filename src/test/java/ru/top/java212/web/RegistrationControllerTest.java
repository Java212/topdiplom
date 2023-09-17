package ru.top.java212.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_GetMapping() throws Exception{
        String url = "/registration";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Registration")));
    }


    // todo falling test: No ModelAndView found with error 403

    @Test
    @WithMockUser
    void test_PostMapping() throws Exception{
        String url = "/registration?userName=John&userLogin=j-88&userPassword=fg89{&startingCapitalUser=100";
        String userName = "John";
        String userLogin = "j-88";
        String userPassword = "fg89{";
        String  startingCapitalUser = String.valueOf((new BigDecimal(1568412)));
        this.mockMvc.perform(post(url))
                 .andExpect(model().size(1))
                .andExpect(status().isOk());
    }
}
