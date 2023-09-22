package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dao.IncomeAmount;
import ru.top.java212.dao.IncomeDbDao;
import ru.top.java212.dao.TotalIncome;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class IncomeDbDaoTest {

    @Autowired
    IncomeDbDao incomeDbDao;

    @Test
    void test_method_findByDateBetween(){
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        List<Integer> list = List.of(35000,25140, 86790);

        List<Integer> result = incomeDbDao.findByDateBetween(startPeriod, endPeriod).stream()
                .map(IncomeAmount::getIncomeAmount).toList();
        Assertions.assertEquals(list, result);
    }
    @Test
    void test_method_findByUserIdAndDateBetween(){
        int userId = 2;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        List<Integer> list = List.of(25140, 86790);

        List<Integer> result = incomeDbDao.findByUserIdAndDateBetween(userId, startPeriod, endPeriod).stream()
                .map(IncomeAmount::getIncomeAmount).toList();
        Assertions.assertEquals(list, result);
    }
    @Test
    void test_method_getIncomesFamilyByCategory(){
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Map<String, Integer> list = Map.of("премия", 146930);
        Map<String, Integer> result = new HashMap<>();
        List<TotalIncome> readFromDb = incomeDbDao.getIncomesFamilyByCategory(startPeriod, endPeriod);
        for(TotalIncome inc: readFromDb){
            result.put(inc.getCategoryName(), inc.getTotal().intValue());
        }
        Assertions.assertEquals(list, result);
    }
    @Test
    void test_method_getIncomesUserByCategory(){
        int userId = 2;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        Map<String, Integer> list = Map.of("премия", 111930);
        Map<String, Integer> result = new HashMap<>();
        List<TotalIncome> readFromDb = incomeDbDao.getIncomesUserByCategory(userId, startPeriod, endPeriod);
        for(TotalIncome inc: readFromDb){
            result.put(inc.getCategoryName(), inc.getTotal().intValue());
        }
        Assertions.assertEquals(list, result);
    }
}
