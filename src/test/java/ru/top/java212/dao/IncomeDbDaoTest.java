package ru.top.java212.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class IncomeDbDaoTest {

    @Autowired
    IncomeDbDao incomeDbDao;


    @Test
    void test_categories_are_mapped() {

        Assertions.assertDoesNotThrow(() -> incomeDbDao.findAll());
    }

    @Test
    void test_method_findByDateBetween() {
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 10, 31);
        List<Integer> list = List.of(55000, 41250, 4000,
                62000, 46500, 6000, 26000,
                10000, 17000,
                84000, 63000, 52000);

        List<Integer> result = incomeDbDao.findByDateBetween(startPeriod, endPeriod).stream()
                .map(IncomeAmount::getIncomeAmount).toList();
        Assertions.assertEquals(list, result);
    }

    @Test
    void test_method_findByUserIdAndDateBetween() {
        int userId = 4;
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 10, 31);
        List<Integer> list = List.of(84000, 63000, 52000);

        List<Integer> result = incomeDbDao.findByUserIdAndDateBetween(userId, startPeriod, endPeriod).stream()
                .map(IncomeAmount::getIncomeAmount).toList();
        Assertions.assertEquals(list, result);
    }

    @Test
    void test_getAllAmountForTheDeletedSource() {
        String removeIncomeSource = "премия";
        int thereMustBe = 150750;
        Long amountFromDb = incomeDbDao.getAllAmountForTheDeletedSource(removeIncomeSource);
        Assertions.assertEquals(thereMustBe, amountFromDb);
    }

    @Test
    void test_getCountRecordsInDbWithoutRemoveSource() {
        String removeIncomeSource = "премия";
        int thereMustBe = 9;
        int countRecordsWithoutRemoveSource = incomeDbDao.getCountRecordsInDbWithoutRemoveSource(removeIncomeSource);
        Assertions.assertEquals(thereMustBe, countRecordsWithoutRemoveSource);
    }
}
