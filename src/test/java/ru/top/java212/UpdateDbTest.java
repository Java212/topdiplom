package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dao.ExpenseCategoryDbDao;
import ru.top.java212.dao.IncomeCategoryDbDao;
import ru.top.java212.model.Expense;
import ru.top.java212.model.Income;

@SpringBootTest
public class UpdateDbTest {
    @Autowired
    UpdateDb updateDb;
    @Autowired
    ExpenseCategoryDbDao expenseCategoryDbDao;

    @Autowired
    IncomeCategoryDbDao incomeCategoryDbDao;

    @Test
    @Disabled
    void test_method_editingCategoryNamesExpense(){
        String currentName = "квартплата";
        String newName = "продукты питания";
        updateDb.editingCategoryNamesExpense(currentName, newName);
        String result = expenseCategoryDbDao.findByNameExpenseCategory(newName).getNameExpenseCategory();
        Assertions.assertEquals(newName, result);
    }

    @Test
    @Disabled
    void test_that_the_record_update_for_ExpenseCategory_was_successful(){
        String currentName = "квартплата";
        String newName = "продукты питания";
        updateDb.editingCategoryNamesExpense(currentName, newName);
        int sumAllExpenses =   expenseCategoryDbDao.findByNameExpenseCategory(newName).getExpenses().stream().mapToInt(Expense::getExpenseAmount).sum();
        Assertions.assertEquals(31000, sumAllExpenses);
    }

    @Test
    @Disabled
    void test_method_editingCategoryNamesIncome(){
        String currentName = "премия";
        String newName = "дивиденды";
        updateDb.editingCategoryNamesIncome(currentName, newName);
        String result = incomeCategoryDbDao.findBySourceIncomeCategory(newName).getSourceIncomeCategory();
        Assertions.assertEquals(newName, result);
    }

    @Test
    @Disabled
    void test_that_the_record_update_for_IncomeSource_was_successful(){
        String currentName = "премия";
        String newName = "дивиденды";
        updateDb.editingCategoryNamesIncome(currentName, newName);
        int sumAllExpenses = incomeCategoryDbDao.findBySourceIncomeCategory(newName).getIncomes().stream().mapToInt(Income::getIncomeAmount).sum();
        Assertions.assertEquals(146930, sumAllExpenses);
    }
}
