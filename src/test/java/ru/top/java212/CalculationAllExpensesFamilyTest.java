package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.family.CalculationAllExpensesFamily;

import java.time.LocalDate;

@SpringBootTest
public class CalculationAllExpensesFamilyTest {

    @Autowired
    CalculationAllExpensesFamily allExpenseFamily;

    @Test
    void test_method_calculationExpensesFamily(){
        int res =31000;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Assertions.assertEquals(res,allExpenseFamily.calculationExpensesFamily(startPeriod,endPeriod));
    }
}
