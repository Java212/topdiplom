package ru.top.java212.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.top.java212.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest (classes = Application.class)
@AutoConfigureMockMvc
public class ViewAllExpensesAndIncomesFamilyTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_GetMapping_Expenses_Family() throws Exception{
        String url = "/total_expense_family";
        this.mockMvc.perform(get(url))
                    .andExpect(status().isOk())
                .andExpect(content().string(containsString("Total family expenses")));
    }

// todo falling test: No ModelAndView found error 403

//    @Test
//    void test_PostMapping_Expenses_Family() throws Exception{
//        String url = "/total_expense_family";
//        int amountCategory1 = 6000;
//        int amountCategory2= 3000;
//        int amountCategory3 = 2000;
//        this.mockMvc.perform(post(url))
//                .andExpect(model().attribute("nameCategory1", amountCategory1))
//                .andExpect(model().attribute("nameCategory2", amountCategory2))
//                .andExpect(model().attribute("nameCategory3", amountCategory3))
//                .andExpect(status().isOk());
//    }

    @Test
    void test_GetMapping_Incomes_Family() throws Exception{
        String url = "/total_income_family";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Total family incomes")));
    }

    // todo сделать тест на класс AllExpensesAndIncomesFamily по доходам
}
