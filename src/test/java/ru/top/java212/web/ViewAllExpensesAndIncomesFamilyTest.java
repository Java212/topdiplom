package ru.top.java212.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ViewAllExpensesAndIncomesFamilyTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()) // enable security for the mock set up
                .build();
    }

    @Test
    @WithMockUser
    void test_GetMapping_Expenses_Family() throws Exception {
        String url = "/total_expense_family";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Total family expenses")));
    }

// todo No ModelAndView found with error 403

    @Test
    @WithMockUser
    void test_PostMapping_Expenses_Family() throws Exception {
        String url = "/total_expense_family"; //?nameCategory1=6000&nameCategory2=3000&nameCategory3=2000";
        String amountCategory1 = "6000";
        String amountCategory2 = "3000";
        String amountCategory3 = "2000";

        this.mockMvc.perform(post(url).with(csrf())
                        .param("nameCategory1", amountCategory1)
                        .param("nameCategory2", amountCategory2)
                        .param("nameCategory3", amountCategory3))
                .andExpect(model().size(1))
                .andExpect(status().isOk());
    }

    @Test
    void test_GetMapping_Incomes_Family() throws Exception {
        String url = "/total_income_family";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Total family incomes")));
    }

    // todo сделать тест на класс AllExpensesAndIncomesFamily по доходам
}
