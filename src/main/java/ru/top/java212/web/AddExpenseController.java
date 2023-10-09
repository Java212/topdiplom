package ru.top.java212.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.dto.ExpenseDto;
import ru.top.java212.model.Expense;
import ru.top.java212.model.ExpenseCategory;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddExpenseController {

    @Autowired
    ExpenseCategoryDbDao expenseCategoryDbDao;

    @Autowired
    ExpenseDbDao expenseDbDao;

    @ModelAttribute("allCategories")
    List<ExpenseCategory> getCategories(){
        List<ExpenseCategory> list = new ArrayList<>();
        expenseCategoryDbDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping("/expenses/add")
    @PreAuthorize("authenticated")
    public ModelAndView getNewExpenseView(){
        ModelAndView mv = new ModelAndView("addExpense");

        mv.addObject("newExpense", new ExpenseDto("default category",0));
        return mv;
    }

    @PostMapping("/expenses/add")
    @PreAuthorize("authenticated")
    public ModelAndView addExpense(@Valid @ModelAttribute  ExpenseDto newExpense, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView("addExpense");

                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal) : new User("sisadmin","admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        if ( bindingResult.hasErrors()){
            return  mv.addObject("newExpense", new ExpenseDto("default category",0));
        } else {

            ExpenseCategory defaultCategory = expenseCategoryDbDao.findByNameExpenseCategory(newExpense.categoryName());

            Expense toBeSaved = new Expense(user, defaultCategory, newExpense.amount());
            expenseDbDao.save(toBeSaved);
            mv.addObject("newExpense", new ExpenseDto("default category",0));
            return mv;
        }
    }
}
