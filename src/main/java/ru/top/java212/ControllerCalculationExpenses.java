package ru.top.java212;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.model.ExpenseAmount;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class ControllerCalculationExpenses {

    @Autowired    ExpenseDbDao expenseDbDao;

    @GetMapping("/expense")
    @PreAuthorize("authenticated")
    public String viewPageExpense(){
        return "expense";
    }

    @PostMapping("/expense")
    @PreAuthorize("authenticated")
    public ModelAndView calculationExpenses(@RequestParam("checkbox") String checkbox,
                                            @RequestParam("startDate") LocalDate startDate,
                                            @RequestParam("endDate") LocalDate endDate){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal) : new User("sisadmin","admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        ModelAndView mv = new ModelAndView("expense");

            if(checkbox.equals("family")){
             mv.addObject("totalExpenseFamily",
                     expenseDbDao.findByDateBetween(startDate, endDate).stream().mapToInt(ExpenseAmount::getExpenseAmount).sum());
            } else {
                if (checkbox.equals("user")){
                    mv.addObject("totalExpenseUser",
                            expenseDbDao.findByUserIdAndDateBetween(user.getId(), startDate, endDate).stream().mapToInt(ExpenseAmount::getExpenseAmount).sum());
                }
            }
        return mv;
    }
}
