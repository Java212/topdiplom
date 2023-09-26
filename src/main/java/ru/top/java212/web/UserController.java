package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.top.java212.dao.QuestionRepository;
import ru.top.java212.model.Question;

public class UserController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questionsu")
    public String listQuestions(Model model) {
        Iterable<Question> questions = questionRepository.findAll();
        model.addAttribute("questionsu", questions);
        return "user-questions";
    }
}
