package ru.top.java212;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.model.ExpenseCategory;
import ru.top.java212.model.IncomeCategory;

@Component
public class UpdateDb {

    private final ExpenseCategoryDbDao expenseCategoryDbDao;
    private final IncomeCategoryDbDao incomeCategoryDbDao;

    @Autowired
    public UpdateDb(ExpenseCategoryDbDao expenseCategoryDbDao, IncomeCategoryDbDao incomeCategoryDbDao) {
        this.expenseCategoryDbDao = expenseCategoryDbDao;
        this.incomeCategoryDbDao = incomeCategoryDbDao;
    }
    public void editingCategoryNamesExpense(String currentName, String newName){
        ExpenseCategory editableCategory = expenseCategoryDbDao.findByNameExpenseCategory(currentName);
        editableCategory.setNameExpenseCategory(newName);
        expenseCategoryDbDao.save(editableCategory);
    }

    public void editingCategoryNamesIncome(String currentName, String newName){
        IncomeCategory editableCategory = incomeCategoryDbDao.findBySourceIncomeCategory(currentName);
        editableCategory.setSourceIncomeCategory(newName);
        incomeCategoryDbDao.save(editableCategory);
    }
}
