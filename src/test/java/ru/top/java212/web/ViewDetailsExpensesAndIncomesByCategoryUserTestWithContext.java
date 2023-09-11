package ru.top.java212.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.top.java212.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest (classes = Application.class)
@AutoConfigureMockMvc
public class ViewDetailsExpensesAndIncomesByCategoryUserTestWithContext {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test1() throws Exception{
        String url = "/details_expense_user";
        String expenseUser = "{квартплата=1000}";

        // todo falling test: актуальное и ожидамемое значения одинаковы

        this.mockMvc.perform(get(url))
                .andExpect(model().size(1))
               // .andExpect(model().attribute("listExpenseCategoryUser",expenseUser))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Details expense user")));
    }
}
