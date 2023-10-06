package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.top.java212.calculationExpensesAndIncomesFamily.CalculationAllExpensesFamily;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.model.ExpenseAmount;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ExpenseDbDaoTest {

    @Autowired
    ExpenseDbDao expenseDbDao;

    @Autowired
    ExpenseCategoryDbDao categoryDbDao;

    @Autowired
    CalculationAllExpensesFamily calculationAllExpensesFamily;

    @Test
    void test_method_findByDateBetween(){
        List<Integer> list = List.of(5000,3000, 23000, 300, 600, 1000,
                                                3000, 5000, 10000, 100, 300, 600,
                                                4000, 1000, 100, 400, 400,
                                                6000, 8000, 36000, 4000, 400, 11000, 40000);
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        List<Integer> result = expenseDbDao.findByDateBetween(startPeriod,endPeriod).stream()
                .map(ExpenseAmount::getExpenseAmount).toList();
        Assertions.assertEquals(list, result);
    }
    @Test
    void test_method_findByUserIdAndDateBetween(){
        int userId = 1;
        List<Integer> list = List.of(5000,3000, 23000, 300, 600, 1000);
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        List<Integer> result = expenseDbDao.findByUserIdAndDateBetween(userId, startPeriod,endPeriod).stream()
                .map(ExpenseAmount::getExpenseAmount).toList();
        Assertions.assertEquals(list, result);
    }

    @Test
    void test_getAllAmountForTheDeletedCategory(){
        String nameRemoveCategory = "покупки непродовольственных товаров";
        int result = 69000;
        int amountFromDb = expenseDbDao.getAllAmountForTheDeletedCategory(nameRemoveCategory);
        Assertions.assertEquals(result, amountFromDb);
    }
    @Test
    void test_getCountRecordsInDbWithoutRemoveCategory(){
        String nameRemoveCategory = "покупки непродовольственных товаров";
        int result = 6;
        int countRecordsWithoutRemoveCategory = expenseDbDao.getCountRecordsInDbWithoutRemoveCategory(nameRemoveCategory);
        Assertions.assertEquals(result, countRecordsWithoutRemoveCategory);
    }
     @Test
     @Transactional
     @Disabled
    void test_transferringTheAmountOfExpensesFromTheDeletedCategory(){
        int amountToAdd = 11500;
         String nameRemoveCategory = "покупки непродовольственных товаров";
        categoryDbDao.deleteByNameExpenseCategory(nameRemoveCategory);
        expenseDbDao.transferringTheAmountOfExpensesFromTheDeletedCategory(amountToAdd);

        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
         Map<String, Long> list = Map.of("коммунальные платежи", 29500L,
                                         "расходы на питание",28500L,
                                         "транспортные расходы", 16000L,
                                         "расходы на мобильную связь и интернет", 13200L,
                                         "покупка лекарственных средств", 24500L,
                                         "непредвиденные расходы", 51500L);
         Assertions.assertEquals(list,calculationAllExpensesFamily.calculationExpensesFamilyByCategory(startPeriod, endPeriod));
    }
}
