package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.AllExpensesFamily;

import java.time.LocalDate;

@Controller
public class ViewDetailsExpensesAndIncomesByCategoryFamily {

    private AllExpensesFamily expensesFamily;

    @Autowired
    public ViewDetailsExpensesAndIncomesByCategoryFamily(AllExpensesFamily expensesFamily) {
        this.expensesFamily = expensesFamily;
    }

    @GetMapping("/details_expense_family")
    public ModelAndView viewPageDetailsExpensesFamily(){
        return getModelAndView();
    }

    public ModelAndView getModelAndView(){
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 9, 30);

        ModelAndView mv = new ModelAndView("details_expense_family");
        mv.addObject("listExpensesFamilyByCategory", expensesFamily.getExpensesByCategory(startPeriod,endPeriod));
        return mv;
    }
}
