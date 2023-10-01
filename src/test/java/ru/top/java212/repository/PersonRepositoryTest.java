package ru.top.java212.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dto.UserRegistrationDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;
import ru.top.java212.service.user.UserService;

@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> personRepository.findAll());
    }

    @Test
    public void test_that_person_name_located_by_user_id(){
        String name = "Andrey";
        String login = "userDTO";
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setName(name);
        userRegistrationDTO.setPhone("+79874564321");
        userRegistrationDTO.setLogin("userDTO");
        userRegistrationDTO.setPassword("userDTO");
        userService.save(userRegistrationDTO);
        User user = userRepository.findByLogin(login);
        Person person = personRepository.findByUser(user);
        Assertions.assertEquals(name,person.getName());
    }
}
