package ru.top.java212.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.top.java212.model.Subject;
import ru.top.java212.model.Teacher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TestSubjectRepository {
    @Autowired
    SubjectRepository subjectRepository;

    @Test
    void test_save_find_delete_subject() {

        Subject subject = subjectRepository.save(new Subject(1, "chemistry", new Teacher(1, "Ivan", "Ivanov", "Ivanovich")));
        Subject actualSubject = subjectRepository.findByTitle("chemistry");

        assertThat(subject).isNotNull();
        assertThat(subject.getId()).isGreaterThan(0);
        assertThat(actualSubject).isEqualTo(subject);

        subjectRepository.deleteById(subject.getId());
        assertThat(subjectRepository.findByTitle("chemistry")).isNull();
    }
}
