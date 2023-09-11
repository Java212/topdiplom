package ru.top.java212.IntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.top.java212.Application;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest (classes = Application.class)
@AutoConfigureMockMvc
public class ViewDetailsExpensesAndIncomesByCategoryUserTestWithContext {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_GetMappingExpUser() throws Exception{
        String url = "/details_expense_user";
        Map<String, Long> listResult = Map.of("квартплата", 1000L);

        this.mockMvc.perform(get(url))
                .andExpect(model().size(1))
                .andExpect(model().attribute("listExpenseCategoryUser",listResult))
                .andExpect(status().isOk());
    }

    @Test
    void test_GetMappingIncFamily() throws Exception{
        String url = "/details_income_user";
        Map<String, Long> listResult = Map.of("премия", 111930L);

        this.mockMvc.perform(get(url))
                .andExpect(model().size(1))
                .andExpect(model().attribute("listIncomeUserBySource",listResult))
                .andExpect(status().isOk());
    }
}
