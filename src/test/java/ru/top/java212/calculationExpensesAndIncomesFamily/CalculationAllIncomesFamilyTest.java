package ru.top.java212.calculationExpensesAndIncomesFamily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dao.AllIncomesFamily;

import java.time.LocalDate;

@SpringBootTest
public class CalculationAllIncomesFamilyTest {

    @Autowired
    AllIncomesFamily allIncomesFamily;

    @Test
    void test_method_calculationIncomesFamily(){
        int result =146930;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Assertions.assertEquals(result,allIncomesFamily.calculationIncomesFamily(startPeriod,endPeriod));
    }
}
