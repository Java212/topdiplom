package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.SubjectDbDao;
import ru.top.java212.model.Subject;

@Controller
@RequestMapping("/")
public class ViewControllerSubject {

    private final SubjectDbDao subjectDao;

    @Autowired
    public ViewControllerSubject(SubjectDbDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @GetMapping
    public ModelAndView getSubject(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("subjectsList", subjectDao.findAll().stream().map(Subject::getSubjectName).toList());
        return mv;
    }
}
