import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.Application;
import ru.top.java212.dao.SubjectDbDao;
import ru.top.java212.model.Subject;

import java.util.List;

@SpringBootTest(classes = Application.class)
public class SubjectDbDaoTest {
    @Autowired
    SubjectDbDao subjectDbDao;

    @Test
    void test(){
        List<String> listSubjects = List.of("Математика", "Химия", "Физика", "Русский язык", "Литература");
        List<String> result = subjectDbDao.findAll().stream().map(Subject::getSubjectName).toList();
        Assertions.assertEquals(listSubjects, result);
    }
}
