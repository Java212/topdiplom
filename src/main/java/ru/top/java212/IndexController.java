package ru.top.java212;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/index", "/"})
public class IndexController {
    private final SubjectsDAO subjectsDAO;
    private final TeachersDAO teachersDAO;

    @Autowired
    public IndexController(SubjectsDAO subjectsDAO, TeachersDAO teachersDAO) {
        this.subjectsDAO = subjectsDAO;
        this.teachersDAO = teachersDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewSubjects() {
        return getModelAndView();
    }

    private ModelAndView getModelAndView() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("subjects_all",
                subjectsDAO.getSubjects().stream().map(subject -> {
                    return new SubjectDTO(subject.getId(), subject.getName(), subject.getTeacher_id());
                }).collect(Collectors.toList())
        );
        mv.addObject("subjects_with_teachers",
                subjectsDAO.getSubjects().stream().filter(subject -> subject.getTeacher_id()!=null).map(subject -> {
                    return new SubjectDTO(subject.getId(), subject.getName(), subject.getTeacher_id());
                }).collect(Collectors.toList())
        );
        mv.addObject("teachers",
                teachersDAO.getTeachers().stream().map(teacher -> {
                    return new TeacherDTO(teacher.getName(), teacher.getId());
                }).collect(Collectors.toList())
        );
        return mv;
    }
}

