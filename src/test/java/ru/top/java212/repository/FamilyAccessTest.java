package ru.top.java212.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Family;
import java.util.NoSuchElementException;

@SpringBootTest
class FamilyAccessTest {

    @Autowired
    FamilyRepository familyRepository;

    Logger logger = LogManager.getLogger(FamilyAccessTest.class);

    @Test
    public void whenGetExistsFamilyByIdFromRepositoryThenReturnEntity() {
        try {
            Family family = familyRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Ивановы", family.getName());
        } catch (NoSuchElementException e) {
            Assertions.fail();
            logger.info("No elements found");
        } catch (NullPointerException e) {
            Assertions.fail();
            logger.info("Repository access error");
        }
    }

    @Test
    public void whenAddAndGetNewFamilyByIdFromRepositoryThenReturnEntity() {
        try {
            Family newFamily = new Family("Сидоровы");
            familyRepository.save(newFamily);
            Family family = familyRepository.findById(2).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("Сидоровы", family.getName());
        } catch (NoSuchElementException e) {
            Assertions.fail();
            logger.info("No elements found");
        } catch (NullPointerException e) {
            Assertions.fail();
            logger.info("Repository access error");
        }
    }
}