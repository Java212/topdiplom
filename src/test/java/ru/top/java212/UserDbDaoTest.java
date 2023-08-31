package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;


@SpringBootTest
public class UserDbDaoTest {

    @Autowired
    UserDbDao userDbDao;

    @Test
    void test_context_is_OK(){
        Assertions.assertDoesNotThrow(() -> userDbDao.findById(1));
    }

    @Test
    void test_save_and_get_is_same(){
        User user = new User("Bob","password", Role.USER, BigDecimal.TEN);
        userDbDao.save(user);
        User userFromDb = userDbDao.findById(1).orElseThrow();
        Assertions.assertEquals(user.getName(),userFromDb.getName());
        Assertions.assertEquals(user.getRole(),userFromDb.getRole());
    }

}
