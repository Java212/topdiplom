package ru.top.java212.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.repository.SubjectRepository;

@Controller
@RequestMapping("/")
public class SubjectViewController {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectViewController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    @GetMapping
    public ModelAndView getSubject(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("subjects", subjectRepository.getSubjectsAndTeachers());
        return mv;
    }
}