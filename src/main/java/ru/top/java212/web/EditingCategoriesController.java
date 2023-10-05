package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.service.RedactRecordsInDb;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.model.ExpenseCategory;
import ru.top.java212.model.IncomeCategory;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class EditingCategoriesController {
    private final RedactRecordsInDb redactRecordsInDb;
    private final ExpenseCategoryDbDao expenseCategoryDao;
    private final IncomeCategoryDbDao incomeCategoryDao;
    @Autowired
    public EditingCategoriesController(RedactRecordsInDb redactRecordsInDb, ExpenseCategoryDbDao expenseCategoryDao, IncomeCategoryDbDao incomeCategoryDao) {
        this.redactRecordsInDb = redactRecordsInDb;
        this.expenseCategoryDao = expenseCategoryDao;
        this.incomeCategoryDao = incomeCategoryDao;
    }

    @GetMapping("/recordsDb/update")
    @PreAuthorize("authenticated")
    public ModelAndView viewPageUpdateRecordsDb(){
        ModelAndView mv = new ModelAndView("changeCategory");
        List<ExpenseCategory> listCategoryExpenses = (List<ExpenseCategory>) expenseCategoryDao.findAll();
        List<IncomeCategory> listCategoryIncomes = (List<IncomeCategory>) incomeCategoryDao.findAll();
        mv.addObject("allCategoriesExpenses", listCategoryExpenses);
        mv.addObject("allCategoriesIncomes", listCategoryIncomes);
        return mv;
    }

    @PostMapping("/recordsDb/update")
    @PreAuthorize("authenticated")
    public ModelAndView editingCategories(@RequestParam("whatToAdd") String whatToAdd,
                                          @RequestParam("altNameCategory") String altNameCategory,
                                          @RequestParam("newNameCategory") String newNameCategory){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal) : new User("sisadmin","admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        ModelAndView mv = new ModelAndView("changeCategory");

            if ( whatToAdd.equals("расходы") ){
                redactRecordsInDb.editingCategoryNamesExpense(altNameCategory, newNameCategory);
            }
            if ( whatToAdd.equals("доходы") ){
                redactRecordsInDb.editingCategoryNamesIncome(altNameCategory, newNameCategory);
            }
            mv.addObject("message", "ok");
        List<ExpenseCategory> listCategoryExpenses = (List<ExpenseCategory>) expenseCategoryDao.findAll();
        List<IncomeCategory> listCategoryIncomes = (List<IncomeCategory>) incomeCategoryDao.findAll();
        mv.addObject("allCategoriesExpenses", listCategoryExpenses);
        mv.addObject("allCategoriesIncomes", listCategoryIncomes);
        return mv;
    }
}
