package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.top.java212.dao.SurveyResultRepository;
import ru.top.java212.model.SurveyResult;

import java.util.List;

@Controller
@RequestMapping("/survey-results")
public class SurveyResultController {

    private final SurveyResultRepository surveyResultRepository;

    public SurveyResultController(SurveyResultRepository surveyResultRepository) {
        this.surveyResultRepository = surveyResultRepository;
    }

    @GetMapping
    public String listSurveyResults(Model model) {
        List<SurveyResult> surveyResults = surveyResultRepository.findAll();
        model.addAttribute("surveyResults", surveyResults);
        return "surveyResult/list";
    }

    @GetMapping("/create")
    public String createSurveyResultForm() {
        return "surveyResult/create";
    }

    @PostMapping("/create")
    public String createSurveyResult(@ModelAttribute SurveyResult surveyResult) {
        surveyResultRepository.save(surveyResult);
        return "redirect:/survey-results";
    }

    // Другие методы для редактирования, удаления и тд
}
