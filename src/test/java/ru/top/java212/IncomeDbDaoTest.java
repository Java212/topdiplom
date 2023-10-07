package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.top.java212.calculationExpensesAndIncomesFamily.CalculationAllIncomesFamily;
import ru.top.java212.dao.IncomeAmount;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.dao.IncomeDbDao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class IncomeDbDaoTest {

    @Autowired
    IncomeDbDao incomeDbDao;

    @Autowired
    IncomeCategoryDbDao incomeCategoryDbDao;

    @Autowired
    CalculationAllIncomesFamily calculationAllIncomesFamily;

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
        int amountFromDb = incomeDbDao.getAllAmountForTheDeletedSource(removeIncomeSource);
        Assertions.assertEquals(thereMustBe, amountFromDb);
    }
    @Test
    void test_getCountRecordsInDbWithoutRemoveSource(){
        String removeIncomeSource = "премия";
        int thereMustBe = 9;
        int countRecordsWithoutRemoveSource = incomeDbDao.getCountRecordsInDbWithoutRemoveSource(removeIncomeSource);
        Assertions.assertEquals(thereMustBe, countRecordsWithoutRemoveSource);
    }
    @Test
    @Transactional
    @Disabled
    void test_transferringTheAmountOfIncomesFromTheDeletedSource(){
        String nameRemoveCategory = "премия";
        int amountToAdd = incomeDbDao.getAllAmountForTheDeletedSource(nameRemoveCategory)/incomeDbDao.getCountRecordsInDbWithoutRemoveSource(nameRemoveCategory);
        incomeCategoryDbDao.deleteBySourceIncomeCategory(nameRemoveCategory);
        incomeDbDao.transferringTheAmountOfIncomesFromTheDeletedSource(amountToAdd);

        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        Map<String, Long> list = Map.of("заработная плата", 251250L,
                                        "доходы от ценных бумаг", 43500L,
                                        "стипендия", 26750L,
                                        "доходы от предпренимательской деятельности", 111500L,
                                        "доходы от других источников", 33750L);
        Assertions.assertEquals(list,calculationAllIncomesFamily.calculationSourceIncomeByCategory(startPeriod, endPeriod));
    }
}
