package ru.top.java212;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/index")
public class SubjectsController {
    private final SubjectsDAO subjectsDAO;

    @Autowired
    public SubjectsController(SubjectsDAO subjectsDAO) {
        this.subjectsDAO = subjectsDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewSubjects() {
        return getModelAndView();
    }

    private ModelAndView getModelAndView() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("subjects",
                subjectsDAO.getSubjects().stream().map(subject -> {
                    return new SubjectDTO(subject.getName(), subject.getId());
                }).collect(Collectors.toList())
        );
        return mv;
    }
}

