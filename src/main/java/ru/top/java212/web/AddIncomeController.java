package ru.top.java212.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.dao.IncomeDbDao;
import ru.top.java212.dto.IncomeDto;
import ru.top.java212.model.Income;
import ru.top.java212.model.IncomeCategory;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;
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
    public String getNewIncomeView(Model model) {
        model.addAttribute("newIncome", new IncomeDto("default source", 0));
        return "addIncome";
    }

    @PostMapping("/incomes/add")
    @PreAuthorize("authenticated")
    public String addIncome(@Valid @ModelAttribute IncomeDto newIncome, BindingResult bindingResult, Model model) {

        if ( bindingResult.hasErrors() ) {
            return "addIncome";
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User("sisadmin", "admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

            IncomeCategory defaultSource = incomeCategoryDao.findBySourceIncomeCategory(newIncome.sourceName());

            Income IncomeToBeSaved = new Income(user, defaultSource, newIncome.amount());
            incomeDbDao.save(IncomeToBeSaved);
            return "addIncome";
        }
}
