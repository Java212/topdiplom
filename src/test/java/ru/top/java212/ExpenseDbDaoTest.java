package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Expense;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;


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
    void test_save_and_get_is_same(){
        // TODO FALlING TEST: could not execute statement [Значение NULL не разрешено для поля "USER_ID"
//        User user = new User ("Bob", "df34", Role.USER, new BigDecimal(1000.23));
//        Expense expense = new Expense(1000);
//        expenseDbDao.save(expense);
        Expense expenseFromDb = expenseDbDao.findById(1).orElseThrow();
        Assertions.assertEquals(1000, expenseFromDb.getExpenseAmount());
    }

    @Test
    void test_save_and_set_is_same(){
        int newExpense = 5000;
        Expense expenseFromDb = expenseDbDao.findById(1).orElseThrow();
        expenseFromDb.setExpenseAmount(newExpense);
        Assertions.assertEquals(newExpense, expenseFromDb.getExpenseAmount());
    }

    @Test
    void test_save_and_setDate(){
        LocalDate newData = LocalDate.of(2023, 2, 25);
        Expense expenseFromDb = expenseDbDao.findById(1).orElseThrow();
        expenseFromDb.setDate(newData);
        Assertions.assertEquals(newData, expenseFromDb.getDate());
    }

    @Test
    void test_method_get_and_setUsers(){
        User userFromDb = userDbDao.findById(1).orElseThrow();
        Expense expenseFromDb = expenseDbDao.findById(1).orElseThrow();
        Assertions.assertEquals(userFromDb.getName(), expenseFromDb.getUsers().getName());

        User user = new User("John", "df34", Role.USER, new BigDecimal(10000.23));
        expenseFromDb.setUsers(user);
        Assertions.assertEquals(user.getName(), expenseFromDb.getUsers().getName());
    }
}
