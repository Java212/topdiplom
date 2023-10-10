package ru.top.java212.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dao.IncomeAmount;
import ru.top.java212.dao.IncomeDbDao;
import ru.top.java212.dao.UserDbDao;
import ru.top.java212.model.Income;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    @Test
    void test_method_findByDateBetween(){
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        List<Integer> list = List.of(55000, 41250, 4000,
                                                62000, 46500, 6000, 26000,
                                                10000, 17000,
                                                84000, 63000, 52000);

        List<Integer> result = incomeDbDao.findByDateBetween(startPeriod, endPeriod).stream()
                .map(IncomeAmount::getIncomeAmount).toList();
        Assertions.assertEquals(list, result);
    }
    @Test
    void test_method_findByUserIdAndDateBetween(){
        int userId = 4;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        List<Integer> list = List.of(84000, 63000, 52000);

        List<Integer> result = incomeDbDao.findByUserIdAndDateBetween(userId, startPeriod, endPeriod).stream()
                .map(IncomeAmount::getIncomeAmount).toList();
        Assertions.assertEquals(list, result);
    }

    @Test
    void test_getAllAmountForTheDeletedSource(){
        String removeIncomeSource = "премия";
        int thereMustBe = 150750;
        Long amountFromDb = incomeDbDao.getAllAmountForTheDeletedSource(removeIncomeSource);
        Assertions.assertEquals(thereMustBe, amountFromDb);
    }
    @Test
    void test_getCountRecordsInDbWithoutRemoveSource(){
        String removeIncomeSource = "премия";
        int thereMustBe = 9;
        int countRecordsWithoutRemoveSource = incomeDbDao.getCountRecordsInDbWithoutRemoveSource(removeIncomeSource);
        Assertions.assertEquals(thereMustBe, countRecordsWithoutRemoveSource);
    }
}
