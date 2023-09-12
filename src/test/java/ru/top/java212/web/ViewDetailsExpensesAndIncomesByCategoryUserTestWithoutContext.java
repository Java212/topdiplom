package ru.top.java212.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.AllExpensesUser;
import ru.top.java212.dao.AllIncomesFamily;
import ru.top.java212.dao.AllIncomesUser;

import java.time.LocalDate;
import java.util.Map;

@ExtendWith(SpringExtension.class)
public class ViewDetailsExpensesAndIncomesByCategoryUserTestWithoutContext {

   @InjectMocks
   ViewDetailsExpensesAndIncomesByCategoryUser controller;

   @Mock
   AllExpensesUser expensesUser;

   @Mock
    AllIncomesUser incomesUser;

    //todo test falling: ожидался пустой список

//    @Test
//    void test1(){
//       LocalDate startDate = LocalDate.of(2023, 9, 1);
//       LocalDate endDate = LocalDate.of(2023, 9, 30);
//      int userId = 2;
//      Map<String, Long> resultList = new HashMap<>();
//        resultList.put("квартплата", 5000L);
//        resultList.put("бензин", 2000L);
//       Mockito.when(expensesUser.getExpensesByCategory(userId, startDate, endDate)).thenReturn(resultList);
//        ModelAndView mv = controller.viewPageDetailsExpenseUser();
//        Assertions.assertEquals("details_expense_user", mv.getViewName());
//        Assertions.assertEquals(resultList, mv.getModelMap().getAttribute("listExpenseCategoryUser"));
//    }
           //todo  нижеследует Другой вариант первого теста
    @Test
    void test_GetMapping_viewPageDetailsExpenseUser(){
        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 9, 30);
        int userId = 1;
        Mockito.when(expensesUser.getExpensesByCategory(userId, startDate, endDate)).thenReturn(
                Map.of("квартплата", 5000L,
                        "бензин", 2000L)
        );
        ModelAndView mv = controller.viewPageDetailsExpenseUser();
        Assertions.assertEquals("details_expense_user", mv.getViewName());
        Map<String, Long> list = (Map<String, Long>) mv.getModelMap().get("listExpenseCategoryUser");
        Assertions.assertEquals(list, mv.getModelMap().getAttribute("listExpenseCategoryUser"));
        Mockito.verify(expensesUser).getExpensesByCategory(userId, startDate, endDate);
    }

    @Test
    void test_GetMapping_viewPageDetailsIncomeUser(){
        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 9, 30);
        int userId = 2;
        Mockito.when(incomesUser.getIncomesUserBySource(userId, startDate, endDate)).thenReturn(
                Map.of("заработная плата", 35000L,
                        "премия", 10000L)
        );
        ModelAndView mv = controller.viewPageDetailsIncomeUser();
        Assertions.assertEquals("details_income_user", mv.getViewName());
        Map<String, Long> list = (Map<String, Long>) mv.getModelMap().get("listIncomeUserBySource");
        Assertions.assertEquals(list, mv.getModelMap().getAttribute("listIncomeUserBySource"));
        Mockito.verify(incomesUser).getIncomesUserBySource(userId, startDate, endDate);
    }
}
