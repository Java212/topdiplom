package ru.top.java212.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Expence;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@SpringBootTest
class ExpenceAccessTest {

    @Autowired
    ExpenceRepository expenceRepository;

    Logger logger = LogManager.getLogger(ExpenceAccessTest.class);

    @Test
    public void whenGetExistsExpenceByIdFromRepositoryThenReturnEntity() {
        try {
            Expence expence = expenceRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Подарки", expence.getName());
        } catch (NoSuchElementException e) {
            Assertions.fail();
            logger.info("No elements found");
        } catch (NullPointerException e) {
            Assertions.fail();
            logger.info("Repository access error");
        }
    }

    @Test
    public void whenAddAndGetNewExpenceByIdFromRepositoryThenReturnEntity() {
        try {
            Expence newExpence = new Expence("Кошачий корм", 500.0, 1, 1, LocalDate.of(2023,8,10));
            expenceRepository.save(newExpence);
            Expence expence = expenceRepository.findById(2).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Кошачий корм", expence.getName());
        } catch (NoSuchElementException e) {
            Assertions.fail();
            logger.info("No elements found");
        } catch (NullPointerException e) {
            Assertions.fail();
            logger.info("Repository access error");
        }
    }
}