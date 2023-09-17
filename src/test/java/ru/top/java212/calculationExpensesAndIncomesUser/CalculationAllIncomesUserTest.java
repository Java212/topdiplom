package ru.top.java212.calculationExpensesAndIncomesUser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class CalculationAllIncomesUserTest {
    @Autowired
    CalculationAllIncomesUser calculationncomesUser;

    @Test
    void test_calculationIncomesUser(){
        int userId=2;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        int result=111930;
        Assertions.assertEquals(result, calculationncomesUser.calculationIncomesUser(userId,startPeriod,endPeriod));
    }

    @Test
    void test_method_calculationIncomesUserBySource(){
        int result = 111930;
        int userId = 2;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Map<String, Long> calculated = calculationncomesUser.calculationIncomesUserBySource(userId, startPeriod, endPeriod);
        Assertions.assertEquals(result, calculated.get("премия"));
    }
}
