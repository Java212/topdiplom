package ru.top.java212.calculationExpensesAndIncomesUser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dao.AllExpensesUser;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class CalculationAllExpensesUserTest {
    @Autowired
    AllExpensesUser expensesUser;

    @Test
    void test_method_calculationExpensesUser() {
        int result = 1000;
        int userId = 1;
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 9, 30);
        Assertions.assertEquals(result, expensesUser.calculationExpensesUser(userId, startPeriod, endPeriod));
    }

    @Test
    void test_method_calculationExpensesUserByCategory() {
        int userId = 2;
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 9, 30);
        Map<String, Long> calculated = expensesUser.getExpensesByCategory(userId, startPeriod, endPeriod);
        Assertions.assertEquals(30000, calculated.get("квартплата"));
    }
}
