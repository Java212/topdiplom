package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.model.ExpenseAmount;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ExpenseDbDaoTest {

    @Autowired
    ExpenseDbDao expenseDbDao;

    @Test
    void test(){
        List<Integer> list = List.of(1000,1000, 29000);
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        /*Assertions.assertEquals(list, expenseDbDao.findByDateBetween(startPeriod,endPeriod).stream()
                .mapToInt(ExpenseAmount::getExpenseAmount).collect(Collectors.toList()));*/
    }
}
