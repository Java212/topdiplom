import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.Application;
import ru.top.java212.ExpenseCategoryDbDao;
import ru.top.java212.UserDbDao;
import ru.top.java212.model.User;

import java.util.List;

@SpringBootTest (classes =  Application.class)
public class modelTest {

    @Autowired
    UserDbDao userDbDao;

    @Autowired
    ExpenseCategoryDbDao categoryDbDao;

    @Test
    void test_1(){
        //User user = new User(1, "Bob",1000.52);
        //userDbDao.save(user);
        //Assertions.assertDoesNotThrow(() -> dao.findById(1));
        //Assertions.assertDoesNotThrow(() -> userDbDao.findAll());
        userDbDao.findAll();
    }

    @Test
    void test_2(){
        //User user = new User(1, "Bob",1000.52);
        //userDbDao.save(user);
        //Assertions.assertDoesNotThrow(() -> dao.findById(1));
        Assertions.assertDoesNotThrow(() -> categoryDbDao.findAll());
    }
}
