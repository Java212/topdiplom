package ru.top.java212.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.top.java212.dao.QuestionRepository;
import ru.top.java212.model.Question;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/question/add")
    public String addQuestion(Model model){
        return "question-add";
    }

    @PostMapping("/question/add")
    public String addPostQuestion(@RequestParam String title,@RequestParam String question, Model model){
        Question questions = new Question(title,question);
		questionRepository.save(questions);
        return "redirect:/question";
    }

    @GetMapping("/question/{id}")
    public String questionDetails(@PathVariable(value = "id") long id, Model model){
        if (!questionRepository.existsById(id)){
            return "redirect:/question";
        }

        Optional<Question> question = questionRepository.findById(id);
        ArrayList<Question> res = new ArrayList<>();
        question.ifPresent(res::add);
        model.addAttribute("question",res);
        return "question-details";
    }

    @GetMapping("/question/{id}/edit")
    public String questionEdit(@PathVariable(value = "id") long id, Model model){
        if (!questionRepository.existsById(id)){
            return "redirect:/question";
        }

        Optional<Question> question = questionRepository.findById(id);
        ArrayList<Question> res = new ArrayList<>();
        question.ifPresent(res::add);
        model.addAttribute("question",res);
        return "question-edit";
    }

    @PostMapping("/question/{id}/edit")
    public String updatePostQuestion(@PathVariable(value = "id") long id,@RequestParam String title,@RequestParam String question, Model model){
        Question questions = questionRepository.findById(id).orElseThrow();
        questions.setTitle(title);
        questions.setQuestion(question);
        questionRepository.save(questions);
        return "redirect:/question";
    }

    @PostMapping("/question/{id}/remove")
    public String deletePostQuestion(@PathVariable(value = "id") long id, Model model){
        Question questions = questionRepository.findById(id).orElseThrow();
        questionRepository.delete(questions);
        return "redirect:/question";
    }

}
