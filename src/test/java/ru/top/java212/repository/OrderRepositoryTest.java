package ru.top.java212.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Period;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> orderRepository.findAll());
    }
    @Test
    public void test_that_user_find_by_id_is_ok(){

    }
    @Test
    public void test_that_user_is_saved(){
        User user = new User("user2","password2");
        user.setId(2);
        Role role = roleRepository.findById(2).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        User userFromDB = userRepository.findById(2).orElseThrow();

    }


}
