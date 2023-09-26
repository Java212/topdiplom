package ru.top.java212.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.top.java212.dao.QuestionRepository;
import ru.top.java212.model.Question;

@Controller
public class QuestionController {
	@Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/question")
    public String question(Model model){
		Iterable<Question> questions = questionRepository.findAll();
        model.addAttribute("question", questions);
        return "question";
    }
}
