package ru.top.java212.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.top.java212.model.Lesson;
import ru.top.java212.model.Subject;
import ru.top.java212.model.Teacher;
import ru.top.java212.repository.SubjectRepository;
import ru.top.java212.repository.TeacherRepository;

import java.util.LinkedList;
import java.util.List;

@Component
public class LessonDbDao implements LessonDao {

    private  final SubjectRepository subjectRepository;

    private final  TeacherRepository teacherRepository;

    @Autowired
    LessonDbDao(SubjectRepository subjectRepository,TeacherRepository teacherRepository){
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Lesson> getSubjectsWithTeacher(){
        List<Lesson> subjectWithTeacherList = new LinkedList<>();
        List<Subject> subjects = subjectRepository.findByTeacherNotNull();
        for(Subject subject:subjects){
                    subjectWithTeacherList.add(new Lesson(subject.getName(),subject.getTeacher().getName()));
        }
        return subjectWithTeacherList;
    }
}
