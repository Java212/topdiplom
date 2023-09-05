package ru.top.java212.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> personRepository.findAll());
    }

}
