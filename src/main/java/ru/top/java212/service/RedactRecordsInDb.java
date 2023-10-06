package ru.top.java212.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.model.ExpenseCategory;
import ru.top.java212.model.IncomeCategory;


@Service
public class RedactRecordsInDb {

    private final ExpenseCategoryDbDao expenseCategoryDbDao;
    private final IncomeCategoryDbDao incomeCategoryDbDao;

    @Autowired
    public RedactRecordsInDb(ExpenseCategoryDbDao expenseCategoryDbDao, IncomeCategoryDbDao incomeCategoryDbDao) {
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
