package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.AllExpensesUser;
import ru.top.java212.dao.AllIncomesUser;

import java.time.LocalDate;

@Controller
public class ViewDetailsExpensesAndIncomesByCategoryUser {
private AllExpensesUser expensesUser;
private AllIncomesUser incomesUser;

    @Autowired
    public ViewDetailsExpensesAndIncomesByCategoryUser(AllExpensesUser expensesUser, AllIncomesUser incomesUser) {
        this.expensesUser = expensesUser;
        this.incomesUser = incomesUser;
    }

    @GetMapping("/details_expense_user")
    public ModelAndView viewPageDetailsExpenseUser(){
       return getModelAndView();
    }

    public ModelAndView getModelAndView(){
        ModelAndView mv = new ModelAndView("details_expense_user");

        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 9, 30);

        //todo нужно сделать, чтобы в userId подставлялся id текущего пользователя
        int userId = 1;

        mv.addObject("listExpenseCategoryUser", expensesUser.getExpensesByCategory(userId, startDate, endDate));
        return mv;
    }

    @GetMapping("/details_income_user")
    public ModelAndView viewPageDetailsIncomeUser(){
        return getModelAndViewInc();
    }

    public ModelAndView getModelAndViewInc(){
        ModelAndView mv = new ModelAndView("details_income_user");

        //todo аналогично как выше сделать подстановку userId

        int userId = 2;
        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 9, 30);

        mv.addObject("listIncomeUserBySource", incomesUser.getIncomesUserBySource(userId,startDate, endDate));
        return mv;
    }
}
