package ru.top.java212.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Address;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;

@SpringBootTest
public class ToolRepositoryTest {
    @Autowired
    ToolRepository toolRepository;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> toolRepository.findAll());
    }
    @Test
    public void test_that_(){
        User user = new User("user1","password1");
        user.setId(2);
        Address address = new Address(2,"district1","street1");
        Tool tool = new Tool("tool1", user, address);
        tool.setId(2);
        toolRepository.save(tool);
    }

}
