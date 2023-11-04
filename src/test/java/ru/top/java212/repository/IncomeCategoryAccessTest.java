package ru.top.java212.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.IncomeCategory;
import java.util.NoSuchElementException;


@SpringBootTest
class IncomeCategoryAccessTest {

    @Autowired
    IncomeCategoryRepository incomeCategoryRepository;

    Logger logger = LogManager.getLogger(IncomeCategoryAccessTest.class);

    @Test
    public void whenGetExistsCategoryByIdFromRepositoryThenReturnEntity() {
        try {
            IncomeCategory category = incomeCategoryRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Зарплата", category.getName());
        } catch (NoSuchElementException e) {
            Assertions.fail();
            logger.info("No elements found");
        } catch (NullPointerException e) {
            Assertions.fail();
            logger.info("Repository access error");
        }
    }

    @Test
    public void whenAddAndGetNewCategoryByIdFromRepositoryThenReturnEntity() {
        try {
            IncomeCategory newCategory = new IncomeCategory("Отпускные");
            incomeCategoryRepository.save(newCategory);
            IncomeCategory category = incomeCategoryRepository.findById(2).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Отпускные", category.getName());
        } catch (NoSuchElementException e) {
            Assertions.fail();
            logger.info("No elements found");
        } catch (NullPointerException e) {
            Assertions.fail();
            logger.info("Repository access error");
        }
    }
}