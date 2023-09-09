package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.user.AllExpensesUser;

import java.time.LocalDate;

@Controller
public class DetailsExpensesCategoryUser {
private AllExpensesUser expensesUser;

    public DetailsExpensesCategoryUser(AllExpensesUser expensesUser) {
        this.expensesUser = expensesUser;
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

}
