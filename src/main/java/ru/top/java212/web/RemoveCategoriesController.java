package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.model.ExpenseCategory;
import ru.top.java212.model.IncomeCategory;
import ru.top.java212.service.RedactRecordsInDb;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RemoveCategoriesController {

    private final RedactRecordsInDb redactRecordsInDb;

    @Autowired
    public RemoveCategoriesController(RedactRecordsInDb redactRecordsInDb) {
        this.redactRecordsInDb = redactRecordsInDb;
    }

    @GetMapping("/recordsDb/remove")
    @PreAuthorize("authenticated")
    public ModelAndView viewPageRemoveRecords() {
        ModelAndView mv = new ModelAndView("removeRecords");
        List<ExpenseCategory> listExpenseCategory = new ArrayList<>();
        redactRecordsInDb.getExpenseCategoryDbDao().findAll().iterator().forEachRemaining(listExpenseCategory::add);
        List<IncomeCategory> listIncomesSource = new ArrayList<>();
        redactRecordsInDb.getIncomeCategoryDbDao().findAll().iterator().forEachRemaining(listIncomesSource::add);
        mv.addObject("allCategoriesExpenses", listExpenseCategory);
        mv.addObject("allSourceIncomes", listIncomesSource);
        return mv;
    }

    @PostMapping("/recordsDb/remove")
    @PreAuthorize("authenticated")
    public ModelAndView removeRecordsFromDb(@RequestParam("whatRemove") String whatRemove,
                                            @RequestParam("nameRemoveCategory") String nameRemoveCategory) {

        ModelAndView mv = new ModelAndView("removeRecords");

        redactRecordsInDb.removeCategory(whatRemove, nameRemoveCategory);

//        List<ExpenseCategory> listExpenseCategory = new ArrayList<>();
//        expenseCategoryDao.findAll().iterator().forEachRemaining(listExpenseCategory::add);
//        List<IncomeCategory> listIncomesSource = new ArrayList<>();
//        incomeCategoryDao.findAll().iterator().forEachRemaining(listIncomesSource::add);

        List<ExpenseCategory> listExpenseCategory = new ArrayList<>();
        redactRecordsInDb.getExpenseCategoryDbDao().findAll().iterator().forEachRemaining(listExpenseCategory::add);
        List<IncomeCategory> listIncomesSource = new ArrayList<>();
        redactRecordsInDb.getIncomeCategoryDbDao().findAll().iterator().forEachRemaining(listIncomesSource::add);
        mv.addObject("allCategoriesExpenses", listExpenseCategory);
        mv.addObject("allSourceIncomes", listIncomesSource);
        mv.addObject("message", "Удаление прошло успешно!");
        return mv;
    }
}
