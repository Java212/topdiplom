package ru.top.java212.IntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


@SpringBootTest
@AutoConfigureMockMvc
public class ViewDetailsExpensesAndIncomesByCategoryFamilyTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_GetMappingExpFamily() throws Exception{
        String url = "/details_expense_family";
        Map<String, Long> listResult = Map.of("квартплата", 31000L);

        this.mockMvc.perform(get(url))
                .andExpect(model().size(1))
                .andExpect(model().attribute("listExpensesFamilyByCategory", listResult))
                .andExpect(status().isOk());
    }

    @Test
    void test_GetMappingIncFamily() throws Exception{
        String url = "/details_income_family";
        Map<String, Long> listResult = Map.of("премия", 146930L);

        this.mockMvc.perform(get(url))
                .andExpect(model().size(1))
                .andExpect(model().attribute("listIncomeFamilyBySource", listResult))
                .andExpect(status().isOk());
    }
}
