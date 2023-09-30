package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.model.ExpenseCategory;
import ru.top.java212.model.IncomeCategory;

@Controller
public class EditingCategoriesController {

   private final ExpenseCategoryDbDao expenseCategoryDbDao;

    private final IncomeCategoryDbDao incomeCategoryDbDao;

    @Autowired
    public EditingCategoriesController(ExpenseCategoryDbDao expenseCategoryDbDao, IncomeCategoryDbDao incomeCategoryDbDao) {
        this.expenseCategoryDbDao = expenseCategoryDbDao;
        this.incomeCategoryDbDao = incomeCategoryDbDao;
    }
    @GetMapping("/newCategory/add")
    public String view(){
        return "addNewCategory";
    }

    @PostMapping("/newCategory/add")
    public ModelAndView addNewCategory(@RequestParam("whatToAdd") String checkbox,
                                       @RequestParam("NameNewCategory") String nameCategory){
        ModelAndView mv = new ModelAndView("addNewCategory");
            if ( checkbox.equals("расходы") ){
                ExpenseCategory newCategory = new ExpenseCategory(nameCategory);
                expenseCategoryDbDao.save(newCategory);
            }
            if ( checkbox.equals("доходы") ){
                IncomeCategory newCategory = new IncomeCategory(nameCategory);
                incomeCategoryDbDao.save(newCategory);
            }
            mv.addObject("message", "Новая категория добавлена в БД");
        return mv;
    }
}
