package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Teacher;

import java.util.List;

@SpringBootTest
class TeachersH2DAOTest {
    @Autowired
    TeachersDAO dao;

    @Test
    public void test_connectivity() {
        Assertions.assertDoesNotThrow(() -> dao.getTeachers());
    }

    @Test
    public void test_get_all_teachers() {
        List<Teacher> list = dao.getTeachers();
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("Петров В.Г.", list.get(1).getName());
    }
}