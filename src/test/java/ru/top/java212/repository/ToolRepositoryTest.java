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
    @Autowired
    UserRepository userRepository;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> toolRepository.findAll());
    }
    @Test
    public void test_that_tool_is_saved(){
        User user = userRepository.findById(1).orElseThrow();
        Address address = new Address(2,"district2","street2");
        Tool tool = new Tool("tool2", user, address);
        tool.setId(2);
        toolRepository.save(tool);
        Tool toolFromDB = toolRepository.findById(2).orElseThrow();
        Assertions.assertEquals("tool2",toolFromDB.getName());
    }

}
