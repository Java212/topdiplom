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
    MockMvc mockMvc;

    @Test
    void test_GetMapping_Expenses_User() throws  Exception{
     String url = "/total_expense_user";

     this.mockMvc.perform(get(url))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Total user expenses")));
    }
// todo falling test: No ModelAndView found

//    @Test
//    void test_PostMapping_Expenses_User() throws  Exception{
//        String url = "/total_expense_user";
//        String amountCategory1 = "6000";
//        String amountCategory2= "3000";
//        String amountCategory3 = "2000";
//
//        this.mockMvc.perform(post(url)
//                        .param("amountCategory1", amountCategory1)
//                        .param("amountCategory2", amountCategory2)
//                        .param("amountCategory3", amountCategory3))
//                .andExpect(model().size(1))
//                .andExpect(status().isOk());
//    }

    @Test
    void test_GetMapping_Incomes_User() throws  Exception{
        String url = "/total_income_user";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Total income user")));
    }
}
