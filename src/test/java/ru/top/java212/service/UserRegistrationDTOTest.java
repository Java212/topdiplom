package ru.top.java212.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dto.UserRegistrationDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.service.user.UserService;

@SpringBootTest
public class UserRegistrationDTOTest {

    @Autowired
    private  UserService userService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test_that_user_and_person_saved_ok() {
        int usersSize = userService.allUsers().size();
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setName("Andrey");
        userRegistrationDTO.setPhone("+79874564321");
        userRegistrationDTO.setLogin("userDTO");
        userRegistrationDTO.setPassword("userDTO");
        userService.save(userRegistrationDTO);
        Assertions.assertEquals(usersSize+1,userService.allUsers().size());
        User user  = (User)userService.loadUserByUsername("userDTO");
        int id = user.getId();
        Person person = personRepository.findById(id).orElseThrow();
        Assertions.assertEquals("Andrey",person.getName());
    }
}
