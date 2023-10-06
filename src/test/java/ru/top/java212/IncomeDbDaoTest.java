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
        String removeIncomeSource = "стипендия";
        int result = 10000;
        int amountFromDb = incomeDbDao.getAllAmountForTheDeletedSource(removeIncomeSource);
        Assertions.assertEquals(result, amountFromDb);
    }
    @Test
    void test_getCountRecordsInDbWithoutRemoveSource(){
        String removeIncomeSource = "стипендия";
        int result = 5;
        int countRecordsWithoutRemoveSource = incomeDbDao.getCountRecordsInDbWithoutRemoveSource(removeIncomeSource);
        Assertions.assertEquals(result, countRecordsWithoutRemoveSource);
    }
    @Test
    @Transactional
    @Disabled
    void test_transferringTheAmountOfIncomesFromTheDeletedSource(){
        int amountToAdd = 2000;
        String nameRemoveCategory = "доходы от ценных бумаг";
        incomeCategoryDbDao.deleteBySourceIncomeCategory(nameRemoveCategory);
        incomeDbDao.transferringTheAmountOfIncomesFromTheDeletedSource(amountToAdd);

        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,10,31);
        Map<String, Long> list = Map.of("коммунальные платежи", 29500L,
                "расходы на питание",28500L,
                "транспортные расходы", 16000L,
                "расходы на мобильную связь и интернет", 13200L,
                "покупка лекарственных средств", 24500L,
                "непредвиденные расходы", 51500L);
        Assertions.assertEquals(list,calculationAllIncomesFamily.calculationSourceIncomeByCategory(startPeriod, endPeriod));
    }

}
