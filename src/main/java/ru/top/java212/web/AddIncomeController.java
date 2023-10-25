package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.dao.IncomeDbDao;
import ru.top.java212.dto.IncomeDto;
import ru.top.java212.model.Income;
import ru.top.java212.model.IncomeCategory;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;
import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddIncomeController {
    @Autowired
    IncomeCategoryDbDao incomeCategoryDao;

    @Autowired
    IncomeDbDao incomeDbDao;

    @ModelAttribute("allSources")
    public List<IncomeCategory> getIncomeCategory() {
        List<IncomeCategory> list = new ArrayList<>();
        incomeCategoryDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping("/incomes/add")
    @PreAuthorize("authenticated")
    public ModelAndView getNewIncomeView() {
        ModelAndView mv = new ModelAndView("addIncome");
        mv.addObject("newIncome", new IncomeDto("default source", 0));
        return mv;
    }

    @PostMapping("/incomes/add")
    @PreAuthorize("authenticated")
    public ModelAndView addIncome(@ModelAttribute IncomeDto newIncome) throws BindException {
        ModelAndView mv = new ModelAndView("addIncome");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User("sisadmin", "admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        if (newIncome.amount() < 0) {
            throw new BindException();
        } else {
            IncomeCategory defaultSource = incomeCategoryDao.findBySourceIncomeCategory(newIncome.sourceName());

            Income IncomeToBeSaved = new Income(user, defaultSource, newIncome.amount());
            incomeDbDao.save(IncomeToBeSaved);
            mv.addObject("newIncome", new IncomeDto("default source", 0));
            return mv;
        }
    }
}
