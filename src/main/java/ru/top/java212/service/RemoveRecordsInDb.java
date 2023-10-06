package ru.top.java212.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.dao.IncomeDbDao;

@Service
public class RemoveRecordsInDb {
    private final ExpenseDbDao expenseDbDao;
    private final IncomeDbDao incomeDbDao;
    private final ExpenseCategoryDbDao categoryDbDao;

    @Autowired
     public RemoveRecordsInDb(ExpenseDbDao expenseDbDao, IncomeDbDao incomeDbDao, ExpenseCategoryDbDao categoryDbDao) {
        this.expenseDbDao = expenseDbDao;
        this.incomeDbDao = incomeDbDao;
        this.categoryDbDao = categoryDbDao;
    }

    @Transactional
    public int removeCategory(String whatRemove, String nameRemoveCategory){
        int numberOfDeletedRecordsInDb = 0;

        if ( whatRemove.equals("расходы")){
            int amountToAddToEachExpenseInTheDb = expenseDbDao.getAllAmountForTheDeletedCategory(nameRemoveCategory)/expenseDbDao.getCountRecordsInDbWithoutRemoveCategory(nameRemoveCategory);
            numberOfDeletedRecordsInDb = categoryDbDao.deleteByNameExpenseCategory(nameRemoveCategory);
            expenseDbDao.transferringTheAmountOfExpensesFromTheDeletedCategory(amountToAddToEachExpenseInTheDb);
        }
        if ( whatRemove.equals("доходы")){
            int sum = 0;

        }
     return numberOfDeletedRecordsInDb;
    }
}
