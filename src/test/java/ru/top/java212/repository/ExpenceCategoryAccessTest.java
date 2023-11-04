package ru.top.java212.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.ExpenceCategory;
import java.util.NoSuchElementException;

@SpringBootTest
class ExpenceCategoryAccessTest {

    @Autowired
    ExpenceCategoryRepository expenceCategoryRepository;

    Logger logger = LogManager.getLogger(ExpenceCategoryAccessTest.class);

    @Test
    public void whenGetExistsCategoryByIdFromRepositoryThenReturnEntity() {
        try {
            ExpenceCategory category = expenceCategoryRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Супермаркеты", category.getName());
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
            ExpenceCategory newCategory = new ExpenceCategory("Налоги");
            expenceCategoryRepository.save(newCategory);
            ExpenceCategory category = expenceCategoryRepository.findById(2).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Налоги", category.getName());
        } catch (NoSuchElementException e) {
            Assertions.fail();
            logger.info("No elements found");
        } catch (NullPointerException e) {
            Assertions.fail();
            logger.info("Repository access error");
        }
    }
}