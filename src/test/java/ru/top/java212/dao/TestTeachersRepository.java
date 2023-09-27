package ru.top.java212.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.top.java212.model.Teacher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TestTeachersRepository {

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    void test_save_find_teacher() {

        Teacher teacher = teacherRepository.save(new Teacher(1, "Ivan", "Ivanov", "Ivanovich"));
        Teacher actualTeacher = teacherRepository.findByName("Ivan");

        assertThat(teacher).isNotNull();
        assertThat(teacher.getId()).isGreaterThan(0);
        assertThat(actualTeacher).isEqualTo(teacher);


    }
}
