package ru.top.java212.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.dao.IncomeDbDao;
import ru.top.java212.model.ExpenseCategory;
import ru.top.java212.model.IncomeCategory;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedactRecordsInDb {

    private final ExpenseDbDao expenseDbDao;
    private final IncomeDbDao incomeDbDao;
    private final ExpenseCategoryDbDao expenseCategoryDbDao;
    private final IncomeCategoryDbDao incomeCategoryDbDao;

    @Autowired
    public RedactRecordsInDb(ExpenseDbDao expenseDbDao, IncomeDbDao incomeDbDao,
                             ExpenseCategoryDbDao expenseCategoryDbDao, IncomeCategoryDbDao incomeCategoryDbDao) {
        this.expenseDbDao = expenseDbDao;
        this.incomeDbDao = incomeDbDao;
        this.expenseCategoryDbDao = expenseCategoryDbDao;
        this.incomeCategoryDbDao = incomeCategoryDbDao;
    }

    @Transactional
    public void editingCategoryNamesExpense(String currentName, String newName) {
        ExpenseCategory editableCategory = expenseCategoryDbDao.findByNameExpenseCategory(currentName);
        editableCategory.setNameExpenseCategory(newName);
        expenseCategoryDbDao.save(editableCategory);
    }

    @Transactional
    public void editingCategoryNamesIncome(String currentName, String newName) {
        IncomeCategory editableCategory = incomeCategoryDbDao.findBySourceIncomeCategory(currentName);
        editableCategory.setSourceIncomeCategory(newName);
        incomeCategoryDbDao.save(editableCategory);
    }

    @Transactional
    public List<ExpenseCategory> removeExpenseCategory(String nameRemoveCategory) {
        Long amountToAddToEachExpenseInTheDb = expenseDbDao.getAllAmountForTheDeletedCategory(nameRemoveCategory) / expenseDbDao.getCountRecordsInDbWithoutRemoveCategory(nameRemoveCategory);
        int numberOfDeletedRecordsInDb = expenseCategoryDbDao.deleteByNameExpenseCategory(nameRemoveCategory);
        expenseDbDao.transferringTheAmountOfExpensesFromTheDeletedCategory(amountToAddToEachExpenseInTheDb);
        return getExpenseCategoryList();
    }

    @Transactional
    public List<IncomeCategory> removeIncomeSource(String nameRemoveCategory) {
        Long amountToAddToEachIncomeInTheDb = incomeDbDao.getAllAmountForTheDeletedSource(nameRemoveCategory)/incomeDbDao.getCountRecordsInDbWithoutRemoveSource(nameRemoveCategory);
        int numberOfDeletedRecordsInDb = incomeCategoryDbDao.deleteBySourceIncomeCategory(nameRemoveCategory);
        incomeDbDao.transferringTheAmountOfIncomesFromTheDeletedSource(amountToAddToEachIncomeInTheDb);
        return getIncomeSourceList();
    }

    public List<ExpenseCategory> getExpenseCategoryList() {
        List<ExpenseCategory> expenseCategoryList = new ArrayList<>();
        expenseCategoryDbDao.findAll().iterator().forEachRemaining(expenseCategoryList::add);
        return expenseCategoryList;
    }

    public List<IncomeCategory> getIncomeSourceList() {
        List<IncomeCategory> incomeCategoryList = new ArrayList<>();
        incomeCategoryDbDao.findAll().iterator().forEachRemaining(incomeCategoryList::add);
        return incomeCategoryList;
    }
}
