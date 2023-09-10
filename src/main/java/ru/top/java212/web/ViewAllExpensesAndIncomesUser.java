package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.AllExpensesUser;
import ru.top.java212.dao.AllIncomesUser;

import java.time.LocalDate;

@Controller
public class ViewAllExpensesAndIncomesUser {

    @Autowired
    AllExpensesUser expensesUser;

    @Autowired
    AllIncomesUser incomesUser;

    @GetMapping("/total_expense_user")
    public String viewPageTotalExpenseUser(){
        return "total_expense_user";
    }

   @PostMapping("/total_expense_user")
    public ModelAndView displayAllExpensesUser(@RequestParam ("nameCategory1") int amountCategory1,
                                               @RequestParam ("nameCategory2") int amountCategory2,
                                               @RequestParam ("nameCategory3") int amountCategory3){

       ModelAndView mv = new ModelAndView("total_expense_user");
       int userId=2;
       LocalDate startPeriod = LocalDate.of(2023,9,1);
       LocalDate endPeriod = LocalDate.of(2023,9,30);
       int totalAmountExpenses = expensesUser.calculationExpensesUser(userId, startPeriod, endPeriod)+amountCategory1+amountCategory2+amountCategory3;
       mv.addObject("allExpenses", totalAmountExpenses);

       return mv;
   }

    @GetMapping("/total_income_user")
    public String viewPageTotalIncomeUser(){
        return "total_income_user";
    }

    @PostMapping("/total_income_user")
    public ModelAndView displayAllIncomesUser(@RequestParam ("sourceIncome1") int amountBySourceIncome1,
                                              @RequestParam ("sourceIncome2") int amountBySourceIncome2,
                                              @RequestParam ("sourceIncome3") int amountBySourceIncome3){
        ModelAndView mv = new ModelAndView("total_income_user");
        int userId=2;
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        int totalAmountIncomes = incomesUser.calculationIncomesUser(userId, startPeriod, endPeriod) + amountBySourceIncome1 + amountBySourceIncome2 + amountBySourceIncome3;
        mv.addObject("allIncomes", totalAmountIncomes);
        return mv;
    }
}
