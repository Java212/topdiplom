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
        List<Teacher> teachers = teacherRepository.findAll();
        for(Subject subject:subjects){
            for (Teacher teacher:teachers){
                if( subject.getTeacher().getId() == teacher.getId()){
                    subjectWithTeacherList.add(new Lesson(subject.getName(),teacher.getName()));
                }
            }
        }
        return subjectWithTeacherList;
    }
}
