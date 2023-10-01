package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Subject;
import java.util.List;

@SpringBootTest
class SubjectsH2DAOTest {
    @Autowired
    SubjectsDAO dao;

    @Test
    public void test_connectivity() {
        Assertions.assertDoesNotThrow(() -> dao.getSubjects());
    }

    @Test
    public void test_get_all_subjects() {
        List<Subject> list = dao.getSubjects();
        Assertions.assertEquals(9, list.size());
        Assertions.assertEquals("Литература", list.get(1).getName());
    }
}