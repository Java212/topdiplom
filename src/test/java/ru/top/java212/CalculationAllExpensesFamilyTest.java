package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.calculationExpensesAndIncomesFamily.CalculationAllExpensesFamily;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class CalculationAllExpensesFamilyTest {

    @Autowired
    CalculationAllExpensesFamily calculationAllExpensesFamily;


    @Test
    void test_method_calculationExpensesFamily(){
        int result = 31000;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Assertions.assertEquals(result,calculationAllExpensesFamily.calculationExpensesFamily(startPeriod, endPeriod));
    }
    @Test
    void test_method_calculationExpensesFamilyByCategory(){

        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Map<String, Long> list = Map.of("квартплата", 31000L);
        Assertions.assertEquals(list,calculationAllExpensesFamily.calculationExpensesFamilyByCategory(startPeriod, endPeriod));
    }
}
