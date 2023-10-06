package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.model.ExpenseAmount;
import ru.top.java212.model.TotalExpense;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ExpenseDbDaoTest {

    @Autowired
    ExpenseDbDao expenseDbDao;

    @Autowired
    ExpenseCategoryDbDao categoryDbDao;

    @Test
    void test_method_findByDateBetween(){
        List<Integer> list = List.of(1000,1000, 29000);
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        List<Integer> result = expenseDbDao.findByDateBetween(startPeriod,endPeriod).stream()
                .map(ExpenseAmount::getExpenseAmount).toList();
        Assertions.assertEquals(list, result);
    }
    @Test
    void test_method_findByUserIdAndDateBetween(){
        int userId = 2;
        List<Integer> list = List.of(1000, 29000);
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        List<Integer> result = expenseDbDao.findByUserIdAndDateBetween(userId, startPeriod,endPeriod).stream()
                .map(ExpenseAmount::getExpenseAmount).toList();
        Assertions.assertEquals(list, result);
    }

    @Test
    void test_method_getExpensesFamilyByCategory(){
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        Map<String, Integer> list = Map.of("квартплата", 31000);

        Map<String, Integer> result = new HashMap<>();
        List<TotalExpense> listFromDb = expenseDbDao.getExpensesFamilyByCategory(startPeriod, endPeriod);
        for(TotalExpense ex : listFromDb){
            result.put(ex.getCategoryName(), ex.getTotal().intValue());
        }
        Assertions.assertEquals(list, result);
    }
    @Test
    void test_method_getExpensesUserByCategory(){
        int userId = 2;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        Map<String, Integer> list = Map.of("квартплата", 30000);

        Map<String, Integer> result = new HashMap<>();
        List<TotalExpense> listFromDb = expenseDbDao.getExpensesUserByCategory(userId, startPeriod, endPeriod);
        for(TotalExpense ex : listFromDb){
            result.put(ex.getCategoryName(), ex.getTotal().intValue());
        }
        Assertions.assertEquals(list, result);
    }

    @Test
    void test_getAllAmountForTheDeletedCategory(){
        String nameRemoveCategory = "квартплата";
        int result = 31000;
        int amountFromDb = expenseDbDao.getAllAmountForTheDeletedCategory(nameRemoveCategory);
        Assertions.assertEquals(result, amountFromDb);
    }
    @Test
    void test_getCountRecordsInDbWithoutRemoveCategory(){
        String nameRemoveCategory = "квартплата";
        int result = 1;
        int amountFromDb = expenseDbDao.getCountRecordsInDbWithoutRemoveCategory(nameRemoveCategory);
        Assertions.assertEquals(result, amountFromDb);
    }
     @Test
     @Transactional
    void test_transferringTheAmountOfExpensesFromTheDeletedCategory(){
        int amountToAdd = 31000;
         String nameRemoveCategory = "квартплата";
        categoryDbDao.deleteByNameExpenseCategory(nameRemoveCategory);
        expenseDbDao.transferringTheAmountOfExpensesFromTheDeletedCategory(amountToAdd);

        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        Map<String, Integer> list = new HashMap<>();

        Map<String, Integer> result = new HashMap<>();
        List<TotalExpense> listFromDb = expenseDbDao.getExpensesFamilyByCategory(startPeriod, endPeriod);
        for(TotalExpense ex : listFromDb){
            result.put(ex.getCategoryName(), ex.getTotal().intValue());
        }
        Assertions.assertEquals(list, result);
    }
}
