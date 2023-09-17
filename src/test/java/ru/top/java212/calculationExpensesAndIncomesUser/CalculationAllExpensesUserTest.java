package ru.top.java212.calculationExpensesAndIncomesUser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class CalculationAllExpensesUserTest {

    @Autowired
    CalculationAllExpensesUser calculationAllExpensesUser;

    @Test
    void test_method_calculationExpensesUser(){
        int userId = 2;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        int result = 30000;
        Assertions.assertEquals(result,calculationAllExpensesUser.calculationExpensesUser(userId, startPeriod, endPeriod));
    }

    @Test
    void test_method_calculationExpensesUserByCategory(){
        int userId = 2;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Map<String, Long> list = Map.of("квартплата", 30000L);
        Assertions.assertEquals(list,calculationAllExpensesUser.calculationExpensesUserByCategory(userId, startPeriod, endPeriod));
    }
}
