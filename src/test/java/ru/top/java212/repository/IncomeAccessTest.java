package ru.top.java212.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Income;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@SpringBootTest
class IncomeAccessTest {

    @Autowired
    IncomeRepository incomeRepository;

    Logger logger = LogManager.getLogger(IncomeAccessTest.class);

    @Test
    public void whenGetExistsIncomeByIdFromRepositoryThenReturnEntity() {
        try {
            Income income = incomeRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Подсчет за июль", income.getName());
        } catch (NoSuchElementException e) {
            Assertions.fail();
            logger.info("No elements found");
        } catch (NullPointerException e) {
            Assertions.fail();
            logger.info("Repository access error");
        }
    }

    @Test
    public void whenAddAndGetNewIncomeByIdFromRepositoryThenReturnEntity() {
        try {
            Income newIncome = new Income("Продажа сапогов", 3000.0, 1, 1, LocalDate.of(2023,8,10));
            incomeRepository.save(newIncome);
            Income income = incomeRepository.findById(2).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Продажа сапогов", income.getName());
        } catch (NoSuchElementException e) {
            Assertions.fail();
            logger.info("No elements found");
        } catch (NullPointerException e) {
            Assertions.fail();
            logger.info("Repository access error");
        }
    }
}