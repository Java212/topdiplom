package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.user.AllExpensesUser;

import java.time.LocalDate;

@SpringBootTest
public class CalculationAllExpensesUserTest {
    @Autowired
    AllExpensesUser expensesUser;

    @Test
    void test_method_calculationExpensesUse() {
        int result = 1000;
        int userId = 1;
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 9, 30);
        Assertions.assertEquals(result, expensesUser.calculationExpensesUser(userId, startPeriod, endPeriod));
    }
}
