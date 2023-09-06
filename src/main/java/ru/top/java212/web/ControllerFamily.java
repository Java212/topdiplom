package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("/total_expense_family")
public class ControllerFamily {

    @GetMapping
    public String viewPages(){
        return "total_expense_family";
    }
    @PostMapping
    public ModelAndView displayExpensesFamily(@ModelAttribute int [] enteredExpenses){
        ModelAndView mv = new ModelAndView("total_expense_family");
        int totalAmountExpenses = 0+ Arrays.stream(enteredExpenses).sum(); //вместо 0 берем значения из БД
        mv.addObject("allExpenses", totalAmountExpenses);
        return mv;
    }
}
