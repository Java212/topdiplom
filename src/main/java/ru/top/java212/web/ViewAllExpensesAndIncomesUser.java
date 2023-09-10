package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.AllExpensesUser;

import java.time.LocalDate;

@Controller
public class ViewAllExpensesAndIncomesUser {

    @Autowired
    AllExpensesUser expensesUser;

    @GetMapping("/total_expense_user")
    public String viewPageTotalExpenseUser(){
        return "total_expense_user";
    }

   @PostMapping("/total_expense_user")
    public ModelAndView displayAllExpensesUser(@RequestParam ("sourceOfIncome1") int amountSource1,
                                               @RequestParam ("sourceOfIncome2") int amountSource2,
                                               @RequestParam ("sourceOfIncome3") int amountSource3){
        ModelAndView mv = new ModelAndView("total_expense_user");
        int userId=2;
       LocalDate startPeriod = LocalDate.of(2023,9,1);
       LocalDate endPeriod = LocalDate.of(2023,9,30);
       int totalAmountExpenses = expensesUser.calculationExpensesUser(userId, startPeriod, endPeriod) + amountSource1
                                                                                                        + amountSource2
                                                                                                        + amountSource3;
       mv.addObject("allExpenses", totalAmountExpenses);
       return mv;
   }
}
