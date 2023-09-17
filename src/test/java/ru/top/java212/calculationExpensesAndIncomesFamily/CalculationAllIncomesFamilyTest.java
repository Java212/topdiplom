package ru.top.java212.calculationExpensesAndIncomesFamily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class CalculationAllIncomesFamilyTest {

    @Autowired
    CalculationAllIncomesFamily calculationIncomes;

    @Test
    void test_method_calculationIncomesFamily(){
        int result =146930;
        LocalDate startDate = LocalDate.of(2023,9,1);
        LocalDate endDate = LocalDate.of(2023,9,30);
        Assertions.assertEquals(result, calculationIncomes.calculationIncomesFamily(startDate, endDate));
    }
    @Test
    void test_method_calculationSourceIncomeByCategory(){
        int result = 146930;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Map<String, Long> calculated = calculationIncomes.calculationSourceIncomeByCategory(startPeriod, endPeriod);
        Assertions.assertEquals(result, calculated.get("премия"));
    }
}