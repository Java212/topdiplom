package ru.top.java212.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.dao.UserDbDao;
import ru.top.java212.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class ExpenseDbDaoTest {

    @Autowired
    ExpenseDbDao expenseDbDao;

    @Autowired
    UserDbDao userDbDao;

    @Test
    void test_categories_are_mapped(){

        Assertions.assertDoesNotThrow(() -> expenseDbDao.findAll());
    }

    @Test
    @Disabled
    void test_save_and_get_is_same(){
        User user = new User ("Jek", "df34","fgfg8", Role.USER, new BigDecimal(1000.23));
        user.setId(6);
        ExpenseCategory expenseCategory = new ExpenseCategory("коммунальные платежи");
        Expense expense = new Expense(user, expenseCategory,1000);
        expenseCategory.setId(1);
        expenseDbDao.save(expense);
        Expense expenseFromDb = expenseDbDao.findById(1).orElseThrow();
        Assertions.assertEquals(1000, expenseFromDb.getExpenseAmount());
    }

    @Test
    @Disabled
    void test_save_and_set_is_same(){
        int newExpense = 5000;
        Expense expenseFromDb = expenseDbDao.findById(1).orElseThrow();
        expenseFromDb.setExpenseAmount(newExpense);
        Assertions.assertEquals(newExpense, expenseFromDb.getExpenseAmount());
    }

    @Test
    @Disabled
    void test_save_and_setDate(){
        LocalDate newData = LocalDate.of(2023, 2, 25);
        Expense expenseFromDb = expenseDbDao.findById(1).orElseThrow();
        expenseFromDb.setDate(newData);
        Assertions.assertEquals(newData, expenseFromDb.getDate());
    }

    @Test
    @Disabled
    void test_method_get_and_setUsers(){
        User userFromDb = userDbDao.findById(1).orElseThrow();
        Expense expenseFromDb = expenseDbDao.findById(1).orElseThrow();
        Assertions.assertEquals(userFromDb.getName(), expenseFromDb.getUsers().getName());

        User user = new User("Jek", "df34","fgfg8", Role.USER, new BigDecimal(10000.23));
        expenseFromDb.setUsers(user);
        Assertions.assertEquals(user.getName(), expenseFromDb.getUsers().getName());
    }

    @Test
    void test_method_findByDateBetween(){
        List<Integer> list = List.of(  5000,3000, 23000, 300, 600, 1000,
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
        int thereMustBe = 69000;
        Long amountFromDb = expenseDbDao.getAllAmountForTheDeletedCategory(nameRemoveCategory);
        Assertions.assertEquals(thereMustBe, amountFromDb);
    }
    @Test
    void test_getCountRecordsInDbWithoutRemoveCategory(){
        String nameRemoveCategory = "покупки непродовольственных товаров";
        int thereMustBe = 21;
        int countRecordsWithoutRemoveCategory = expenseDbDao.getCountRecordsInDbWithoutRemoveCategory(nameRemoveCategory);
        Assertions.assertEquals(thereMustBe, countRecordsWithoutRemoveCategory);
    }
}
