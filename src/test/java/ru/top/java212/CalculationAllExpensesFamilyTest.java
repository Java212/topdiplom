package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.family.AllExpensesFamily;

import java.time.LocalDate;

@SpringBootTest
public class CalculationAllExpensesFamilyTest {

//    @Autowired
//    CalculationAllExpensesFamily allExpenseFamily;  это можно убрать реализация через интерфейс

    @Autowired
    AllExpensesFamily expensesFamily;
    @Test
    void test_method_calculationExpensesFamily(){
        int result =31000;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Assertions.assertEquals(result,expensesFamily.calculationExpensesFamily(startPeriod,endPeriod));
    }
}
