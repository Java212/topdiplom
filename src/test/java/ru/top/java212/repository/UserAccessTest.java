package ru.top.java212.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.ExpenceCategory;
import ru.top.java212.model.User;
import ru.top.java212.model.UserRole;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAccessTest {

    @Autowired
    UserRepository userRepository;

    Logger logger = LogManager.getLogger(UserAccessTest.class);

//    @Test
//    public void whenGetExistsUserByIdFromRepositoryThenReturnEntity() {
//        try {
//            User user = userRepository.findById(1).orElseThrow(NoSuchElementException::new);
//            Assertions.assertEquals("Иван", user.getName());
//        } catch (NoSuchElementException e) {
//            Assertions.fail();
//            logger.info("No elements found");
//        } catch (NullPointerException e) {
//            Assertions.fail();
//            logger.info("Repository access error");
//        }
//    }
//
//    @Test
//    public void whenAddAndGetNewUserByIdFromRepositoryThenReturnEntity() {
//        try {
//            User newUser = new User("Петр","qwerty",1);
//            userRepository.save(newUser);
//            User user = userRepository.findById(2).orElseThrow(NoSuchElementException::new);
//            Assertions.assertEquals("Петр", user.getName());
//        } catch (NoSuchElementException e) {
//            Assertions.fail();
//            logger.info("No elements found");
//        } catch (NullPointerException e) {
//            Assertions.fail();
//            logger.info("Repository access error");
//        }
//    }

}