package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.calculationExpensesAndIncomesFamily.CalculationAllIncomesFamily;
import ru.top.java212.calculationExpensesAndIncomesUser.CalculationAllIncomesUser;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class ViewDetailsIncomesController {

    @Autowired
    CalculationAllIncomesFamily calculationIncomesFamily;
    @Autowired
    CalculationAllIncomesUser calculationIncomesUser;

    @GetMapping("/details/incomes")
    @PreAuthorize("authenticated")
    public String viewDetailsExpenses(){
        return "details-income";
    }

    @PostMapping("/details/incomes")
    @PreAuthorize("authenticated")
    public ModelAndView viewDetailsIncomes(@RequestParam("checkbox") String checkbox,
                                            @RequestParam("startDate") LocalDate startDate,
                                            @RequestParam("endDate") LocalDate endDate){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal) : new User("sisadmin","admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        ModelAndView mv = new ModelAndView("details-income");

        if ( checkbox.equals("family") ){
           mv.addObject("detailsListIncomesFamily", calculationIncomesFamily.calculationSourceIncomeByCategory(startDate, endDate));
        } else {
            if ( checkbox.equals("user") ){
                mv.addObject("detailsListIncomesUser", calculationIncomesUser.calculationIncomesUserBySource(user.getId(), startDate, endDate));
            }
        }
        mv.addObject("forWhomCalculation", checkbox);
       return mv;
    }
}
