package ru.top.java212;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.top.java212.user.AllExpensesUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest (classes = Application.class)
@AutoConfigureMockMvc
public class DetailsExpensesCategoruUserTestWithContext {
    @Autowired
    AllExpensesUser expensesUser;

    @Autowired
    MockMvc mockMvc;

    @Test
    void test1() throws Exception{
        String url = "/details_expense_user";
        this.mockMvc.perform(get(url))
                .andExpect(model().size(1))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Details expense user")));
    }
}
