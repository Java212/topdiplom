package ru.top.java212.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Income;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;


@SpringBootTest
public class IncomeDbDaoTest {

    @Autowired
    IncomeDbDao incomeDbDao;

    @Autowired
    UserDbDao userDbDao;

    @Test
    void test_categories_are_mapped(){

        Assertions.assertDoesNotThrow(() -> incomeDbDao.findAll());
    }

    @Test
    @Disabled
    void test_save_and_get_is_same(){
        Income incomeeFromDb = incomeDbDao.findById(1).orElseThrow();
        Assertions.assertEquals(55000, incomeeFromDb.getIncomeAmount());
    }

    @Test
    @Disabled
    void test_save_and_set_is_same(){
        int newIncome = 5000;
        Income incomeeFromDb = incomeDbDao.findById(1).orElseThrow();
        incomeeFromDb.setIncomeAmount(newIncome);
        Assertions.assertEquals(newIncome, incomeeFromDb.getIncomeAmount());
    }

    @Test
    @Disabled
    void test_save_and_setDate(){
        LocalDate newData = LocalDate.of(2023, 4, 10);
        Income incomeFromDb = incomeDbDao.findById(1).orElseThrow();
        incomeFromDb.setDate(newData);
        Assertions.assertEquals(newData, incomeFromDb.getDate());
    }

    @Test
    @Disabled
    void test_method_get_and_setUsers(){
        User userFromDb = userDbDao.findById(1).orElseThrow();
        Income incomeFromDb = incomeDbDao.findById(1).orElseThrow();
        Assertions.assertEquals(userFromDb.getName(), incomeFromDb.getUser().getName());

        User user = new User("Kurt", "df34","fgfg8", Role.USER, new BigDecimal(240000.56));
        incomeFromDb.setUser(user);
        Assertions.assertEquals(user.getName(), incomeFromDb.getUser().getName());
    }
}
