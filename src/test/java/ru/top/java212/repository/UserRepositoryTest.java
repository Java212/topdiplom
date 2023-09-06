package ru.top.java212.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.User;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> userRepository.findAll());
    }
    @Test
    public void test_that_(){
        User userFromDB = userRepository.findById(1).orElseThrow();
        Assertions.assertEquals("user1",userFromDB.getLogin());
    }

}
