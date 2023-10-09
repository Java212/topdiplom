package ru.top.java212.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.top.java212.EditingRecordsInDb;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.dao.IncomeDbDao;
import ru.top.java212.model.ExpenseCategory;
import ru.top.java212.model.IncomeCategory;

@Service
public class RedactRecordsInDb implements EditingRecordsInDb {

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
    @Override
    public void editingCategoryNamesExpense(String currentName, String newName){
        ExpenseCategory editableCategory = expenseCategoryDbDao.findByNameExpenseCategory(currentName);
        editableCategory.setNameExpenseCategory(newName);
        expenseCategoryDbDao.save(editableCategory);
    }
    @Override
    public void editingCategoryNamesIncome(String currentName, String newName){
        IncomeCategory editableCategory = incomeCategoryDbDao.findBySourceIncomeCategory(currentName);
        editableCategory.setSourceIncomeCategory(newName);
        incomeCategoryDbDao.save(editableCategory);
    }

    @Override
    @Transactional
    public int removeCategory(String whatRemove, String nameRemoveCategory){
        int numberOfDeletedRecordsInDb = 0;

        if ( whatRemove.equals("расходы")){
            Long amountToAddToEachExpenseInTheDb = expenseDbDao.getAllAmountForTheDeletedCategory(nameRemoveCategory)/expenseDbDao.getCountRecordsInDbWithoutRemoveCategory(nameRemoveCategory);
            numberOfDeletedRecordsInDb = expenseCategoryDbDao.deleteByNameExpenseCategory(nameRemoveCategory);
            expenseDbDao.transferringTheAmountOfExpensesFromTheDeletedCategory(amountToAddToEachExpenseInTheDb);
        }
        if ( whatRemove.equals("доходы")){
            Long amountToAddToEachIncomeInTheDb = incomeDbDao.getAllAmountForTheDeletedSource(nameRemoveCategory)/incomeDbDao.getCountRecordsInDbWithoutRemoveSource(nameRemoveCategory);
            numberOfDeletedRecordsInDb = incomeCategoryDbDao.deleteBySourceIncomeCategory(nameRemoveCategory);
            incomeDbDao.transferringTheAmountOfIncomesFromTheDeletedSource(amountToAddToEachIncomeInTheDb);
        }
        return numberOfDeletedRecordsInDb;
    }
}
