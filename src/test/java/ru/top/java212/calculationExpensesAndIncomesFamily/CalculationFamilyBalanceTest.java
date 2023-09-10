package ru.top.java212.calculationExpensesAndIncomesFamily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.calculationExpensesAndIncomesFamily.CalculationFamilyBalance;

import java.time.LocalDate;

@SpringBootTest
public class CalculationFamilyBalanceTest {

    @Autowired
    CalculationFamilyBalance balance;

    @Test
    void test_method_getBalance(){
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Assertions.assertEquals(241930, balance.getBalance(startPeriod, endPeriod));
    }
}
