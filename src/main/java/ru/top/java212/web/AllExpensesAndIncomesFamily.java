package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.family.AllExpensesFamily;
import ru.top.java212.family.AllIncomesFamily;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;


import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class AllExpensesAndIncomesFamily {

    @Autowired
    AllExpensesFamily expensesFamily;

    @Autowired
    AllIncomesFamily incomesFamily;


    @GetMapping("/total_expense_family")
    public String viewPageTotalExpenseFamily(){
        return "total_expense_family";
    }

    @PostMapping("/total_expense_family")
    @PreAuthorize("authenticated")
    public ModelAndView displayExpensesFamily(@RequestParam ("nameCategory1") int amountCategory1,
                                              @RequestParam ("nameCategory2") int amountCategory2,
                                              @RequestParam ("nameCategory3") int amountCategory3){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal) : new User("sisadmin","admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        ModelAndView mv = new ModelAndView("total_expense_family");
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);
        int totalAmountExpenses = expensesFamily.calculationExpensesFamily(startPeriod, endPeriod)+amountCategory1+amountCategory2+amountCategory3;
        mv.addObject("allExpenses", totalAmountExpenses);

       //todo проблема сохранения в БД данных введных пользователем

        return mv;
    }

    //todo после решения проблем у Expenses аналогично сделать для Incomes

    @GetMapping("/total_income_family")
    public String viewPageTotalIncomeFamily(){
        return "total_income_family";
    }

    @PostMapping("/total_income_family")
    public ModelAndView displayIncomesFamily(@RequestParam ("sourceIncome1") int amountSourceIncome1,
                                              @RequestParam ("sourceIncome1") int amountSourceIncome2,
                                              @RequestParam ("sourceIncome1") int amountSourceIncome3){

        ModelAndView mv = new ModelAndView("total_income_family");
        LocalDate startPeriod = LocalDate.of(2023,9,1);
        LocalDate endPeriod = LocalDate.of(2023,9,30);

        int totalAmountIncomes = incomesFamily.calculationIncomesFamily(startPeriod, endPeriod) + amountSourceIncome1 + amountSourceIncome2 + amountSourceIncome3;
        mv.addObject("allIncomes", totalAmountIncomes);
        return mv;
    }
}
