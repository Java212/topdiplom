package ru.top.java212;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest (classes = Application.class)
@AutoConfigureMockMvc
public class AllExpensesAndIncomesFamilyTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_GetMapping_Expenses() throws Exception{
        String url = "/total_expense_family";
        this.mockMvc.perform(get(url))
                    .andExpect(status().isOk())
                .andExpect(content().string(containsString("Total family expenses")));
    }

//todo falling test: No ModelAndView found error 403

//    @Test
//    void test_PostMapping() throws Exception{
//        String url = "/total_expense_family";
//        int amountForUtilityBills = 6000;
//        int amountOfGasolineConsumption = 3000;
//        int amountOfTripToTheStore = 2000;
//        this.mockMvc.perform(post(url))
//                .andExpect(model().size(3))
//                .andExpect(model().attribute("nameCategory1", amountForUtilityBills))
//                .andExpect(model().attribute("nameCategory2", amountOfGasolineConsumption))
//                .andExpect(model().attribute("nameCategory3", amountOfTripToTheStore))
//                .andExpect(status().isOk());
//    }

    @Test
    void test_GetMapping_Incomes() throws Exception{
        String url = "/total_income_family";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Total family incomes")));
    }
}
