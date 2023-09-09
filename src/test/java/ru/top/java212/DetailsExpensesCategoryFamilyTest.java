package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.family.AllExpensesFamily;
import ru.top.java212.web.DetailsExpensesCategoryFamily;

import java.time.LocalDate;
import java.util.Map;

@ExtendWith(SpringExtension.class)
public class DetailsExpensesCategoryFamilyTest {

    @InjectMocks
    DetailsExpensesCategoryFamily controller;

    @Mock
    AllExpensesFamily expensesFamily;

    @Test
    void test(){
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 9, 30);
        Mockito.when(expensesFamily.getExpensesByCategory(startPeriod, endPeriod)).thenReturn(
                Map.of("квартплата", 5000L,
                        "бензин", 2000L)
        );
        ModelAndView mv = controller.viewPageDetailsExpensesFamily();
        Assertions.assertEquals("details_expense_family", mv.getViewName());

        Map<String, Long> listexpensesByCategory = (Map<String, Long>) mv.getModelMap().get("listExpensesFamilyByCategory");
        Assertions.assertEquals(listexpensesByCategory, mv.getModelMap().getAttribute("listExpensesFamilyByCategory"));
    }
}
