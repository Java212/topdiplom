package ru.top.java212.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Address;
import ru.top.java212.model.Period;

import java.time.LocalDate;

@SpringBootTest
public class PeriodRepositoryTest {
    @Autowired
    PeriodRepository periodRepository;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> periodRepository.findAll());
    }
    @Test
    public void test_that_period_is_saved(){
        LocalDate startDate = LocalDate.of(2023,5,10);
        LocalDate stopDate = LocalDate.of(2023,6,21);
        periodRepository.save(new Period(1,startDate,stopDate));
        Period periodFromDB = periodRepository.findById(1).orElseThrow();
        Assertions.assertEquals(startDate,periodFromDB.getStartDate());
    }
}
