package ru.top.java212.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.AllExpensesFamily;
import ru.top.java212.dao.AllIncomesFamily;

import java.time.LocalDate;
import java.util.Map;

@ExtendWith(SpringExtension.class)
public class ViewDetailsExpensesAndIncomesByCategoryFamilyTest {

    @InjectMocks
    ViewDetailsExpensesAndIncomesByCategoryFamily controller;

    @Mock
    AllExpensesFamily expensesFamily;

    @Mock
    AllIncomesFamily incomesFamily;


    @Test
    void test_GetMapping_viewPageDetailsExpensesFamily(){
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 9, 30);
        Mockito.when(expensesFamily.getExpensesByCategory(startPeriod, endPeriod)).thenReturn(
                Map.of("квартплата", 5000L,
                        "бензин", 2000L)
        );
        ModelAndView mv = controller.viewPageDetailsExpensesFamily();
        Assertions.assertEquals("details_expense_family", mv.getViewName());

        Map<String, Long> listExpensesByCategory = (Map<String, Long>) mv.getModelMap().get("listExpensesFamilyByCategory");
        Assertions.assertEquals(listExpensesByCategory, mv.getModelMap().getAttribute("listExpensesFamilyByCategory"));
    }

    @Test
    void test_GetMapping_viewPageDetailsIncomeFamily(){
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 9, 30);

        Mockito.when(incomesFamily.getIncomesFamilyBySource(startPeriod, endPeriod)).thenReturn(
                Map.of("заработная плата", 36000L,
                        "премия", 12000L)
        );
        ModelAndView mv = controller.getModelAndViewInc();
        Assertions.assertEquals("details_income_family", mv.getViewName());
        Map<String, Long> listIncomesByCategory = (Map<String, Long>) mv.getModelMap().get("listIncomeFamilyBySource");
        Assertions.assertEquals(listIncomesByCategory, mv.getModelMap().getAttribute("listIncomeFamilyBySource"));
    }
}
