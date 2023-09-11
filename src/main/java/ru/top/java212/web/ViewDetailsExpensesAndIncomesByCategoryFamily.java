package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.AllExpensesFamily;
import ru.top.java212.dao.AllIncomesFamily;

import java.time.LocalDate;

@Controller
public class ViewDetailsExpensesAndIncomesByCategoryFamily {

    private AllExpensesFamily expensesFamily;

    private AllIncomesFamily incomesFamily;

    @Autowired
    public ViewDetailsExpensesAndIncomesByCategoryFamily(AllExpensesFamily expensesFamily, AllIncomesFamily incomesFamily) {
        this.expensesFamily = expensesFamily;
        this.incomesFamily = incomesFamily;
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

    @GetMapping("/details_income_family")
    public ModelAndView viewPageDetailsIncomeFamily(){
        return getModelAndViewInc();
    }

    public ModelAndView getModelAndViewInc(){
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 9, 30);

        ModelAndView mv = new ModelAndView("details_income_family");
        mv.addObject("listIncomeFamilyBySource", incomesFamily.getIncomesFamilyBySource(startPeriod, endPeriod));
        return mv;
    }
}
