package ru.top.java212.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Address;
import ru.top.java212.model.Tool;

@SpringBootTest
public class ToolRepositoryTest {
    @Autowired
    ToolRepository toolRepository;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> toolRepository.findAll());
    }
}
