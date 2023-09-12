package ru.top.java212.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.top.java212.model.Address;
import ru.top.java212.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest

public class UserRepositoryTest {


    @Autowired
    UserRepository userRepository;

    @Test
    void test_save_find_delete_user() {

        User user = userRepository.save(new User(1,"user", "pass", "user@mail.com", new Address(1,"District", "street", 1, 2)));
        User actualUser = userRepository.findByUserName("user");

        assertThat(user).isNotNull();
        assertThat(user.getId()).isGreaterThan(0);
        assertThat(actualUser).isEqualTo(user);

        userRepository.deleteById(user.getId());
        assertThat(userRepository.findByUserName("user")).isNull();
    }


}