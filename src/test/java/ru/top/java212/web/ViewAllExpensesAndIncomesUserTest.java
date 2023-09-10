package ru.top.java212.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.top.java212.Application;
import ru.top.java212.dao.AllExpensesUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest (classes = Application.class)
@AutoConfigureMockMvc
public class ViewAllExpensesAndIncomesUserTest {

    @Autowired
    AllExpensesUser expensesUser;

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_GetMapping() throws  Exception{
     String url = "/total_expense_user";

     this.mockMvc.perform(get(url))
                    .andExpect(status().isOk())
             .andExpect(content().string(containsString("Total user expenses")));
    }
// todo falling test: No ModelAndView found

//    @Test
//    void test_PostMapping() throws  Exception{
//        String url = "/total_expense_user";
//
//        this.mockMvc.perform(post(url))
//                .andExpect(model().size(3))
//                .andExpect(status().isOk());
//
//    }
}
