package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.family.AllExpensesFamily;

import java.time.LocalDate;

@Controller
@RequestMapping("/total_expense_family")
public class ControllerFamily {

    @Autowired
    AllExpensesFamily expensesFamily;

    @GetMapping
    public String viewPages(){
        return "total_expense_family";
    }
    @PostMapping
    public ModelAndView displayExpensesFamily(@RequestParam ("nameCategory1") int amountCategory1,
                                              @RequestParam ("nameCategory2") int amountCategory2,
                                              @RequestParam ("nameCategory3") int amountCategory3) {
        ModelAndView mv = new ModelAndView("total_expense_family");
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);

        int totalAmountExpenses = expensesFamily.calculationExpensesFamily(startPeriod, endPeriod)+amountCategory1+amountCategory2+amountCategory3;
        mv.addObject("allExpenses", totalAmountExpenses);
        return mv;
    }
}
