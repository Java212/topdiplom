package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Subject;
import ru.top.java212.model.Teacher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class IndexControllerTest {
    @InjectMocks
    IndexController controller;
    @Mock
    SubjectsDAO subjectsDAO;
    @Mock
    TeachersDAO teachersDAO;

    @Test
    void testGetAll() throws Exception {
        Mockito.when(subjectsDAO.getSubjects()).thenReturn(
                List.of(new Subject(1,"test1",1),
                        new Subject(2,"test2",null),
                        new Subject(3,"test3",3))
        );
        ModelAndView mv = controller.viewSubjects();
        Assertions.assertEquals("index", mv.getViewName());
        List<Subject> subjects = (List) mv.getModelMap().get("subjects_all");
        Assertions.assertEquals(3, subjects.size());
    }

    @Test
    void testGetWithTeachers() throws Exception {
        Mockito.when(subjectsDAO.getSubjects()).thenReturn(
                List.of(new Subject(1,"test1",1),
                        new Subject(2,"test1",null),
                        new Subject(3,"test3",2))
        );
        ModelAndView mv = controller.viewSubjects();
        Assertions.assertEquals("index", mv.getViewName());
        List<Subject> subjects = (List) mv.getModelMap().get("subjects_with_teachers");
        Assertions.assertEquals(2, subjects.size());
    }

    @Test
    void testGetTeachers() throws Exception {
        Mockito.when(teachersDAO.getTeachers()).thenReturn(
                List.of(new Teacher(1,"test_teacher1"),
                        new Teacher(1,"test_teacher2"))
        );
        ModelAndView mv = controller.viewSubjects();
        Assertions.assertEquals("index", mv.getViewName());
        List<Teacher> subjects = (List) mv.getModelMap().get("teachers");
        Assertions.assertEquals(2, subjects.size());
    }
}