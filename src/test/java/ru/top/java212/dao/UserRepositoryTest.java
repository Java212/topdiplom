package ru.top.java212.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.top.java212.model.Address;
import ru.top.java212.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest

public class UserRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    void test_save_find_delete_user() {
        Address address = entityManager.persist(new Address("District", "street", 1, 2));
        User user = userRepository.save(new User("user", "pass", "user@mail.com", address));
        User actualUser = userRepository.findByUserName("user");

        assertThat(user).isNotNull();
        assertThat(user.getId()).isGreaterThan(0);
        assertThat(actualUser).isEqualTo(user);

        userRepository.deleteById(user.getId());
        assertThat(userRepository.findByUserName("user")).isNull();
    }


}