package ru.top.java212.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Lesson;

import java.util.List;

@SpringBootTest
public class LessonDbDaoTest {
    @Autowired
    LessonDao lessonDao;
    @Test
    public void test(){
        List<Lesson > lessons = lessonDao.getSubjectsWithTeacher();
        Assertions.assertNotNull(lessons);
        Assertions.assertEquals("Алгебра",lessons.get(0).getSubjectName());
    }
}
