package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.repository.SubjectRepository;


@Controller
public class ViewController {

    @Autowired
    private SubjectRepository subjectRepository;



    @GetMapping("/")
    public ModelAndView indexGet(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("subjects", subjectRepository.findAll());
        return mv;
    }


}
