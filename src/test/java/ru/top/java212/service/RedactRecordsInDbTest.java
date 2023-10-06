package ru.top.java212.service;

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
public class RedactRecordsInDbTest {
    @Autowired
    RedactRecordsInDb redactRecordsInDb;
    @Autowired
    ExpenseCategoryDbDao expenseCategoryDbDao;

    @Autowired
    IncomeCategoryDbDao incomeCategoryDbDao;

    @Test
    @Disabled
    void test_method_editingCategoryNamesExpense(){
        String currentName = "расходы на питание";
        String newName = "продукты питания";

        redactRecordsInDb.editingCategoryNamesExpense(currentName, newName);
        String result = expenseCategoryDbDao.findByNameExpenseCategory(newName).getNameExpenseCategory();
        Assertions.assertEquals(newName, result);

        int sumAllExpenses = expenseCategoryDbDao.findByNameExpenseCategory(newName).getExpenses().stream().mapToInt(Expense::getExpenseAmount).sum();
        Assertions.assertEquals(17000, sumAllExpenses);
    }

    @Test
    @Disabled
    void test_method_editingCategoryNamesIncome(){
        String currentName = "доходы от ценных бумаг";
        String newName = "дивиденды";

        redactRecordsInDb.editingCategoryNamesIncome(currentName, newName);
        String result = incomeCategoryDbDao.findBySourceIncomeCategory(newName).getSourceIncomeCategory();
        Assertions.assertEquals(newName, result);

        int sumAllExpenses = incomeCategoryDbDao.findBySourceIncomeCategory(newName).getIncomes().stream().mapToInt(Income::getIncomeAmount).sum();
        Assertions.assertEquals(10000, sumAllExpenses);
    }
}
