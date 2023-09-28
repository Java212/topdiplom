package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.LessonDao;
import ru.top.java212.model.Lesson;
import ru.top.java212.repository.SubjectRepository;

import java.util.List;


@Controller
public class ViewController {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private LessonDao lessonDAO;



    @GetMapping("/")
    public ModelAndView indexGet(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("subjects", subjectRepository.findAll());
        List<Lesson> lessons = lessonDAO.getSubjectsWithTeacher();
        mv.addObject("subjectsWithTeacher",  lessons );
        return mv;
    }
}
