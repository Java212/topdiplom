package ru.top.java212.calculationExpensesAndIncomesUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllExpensesUser;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.model.ExpenseAmount;
import ru.top.java212.model.TotalExpense;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalculationAllExpensesUser implements AllExpensesUser {

    private final ExpenseDbDao expenseDao;

    @Autowired
    public CalculationAllExpensesUser(ExpenseDbDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    @Override
    public int calculationExpensesUser(int userId, LocalDate initialDate, LocalDate endDate) {
        List<ExpenseAmount> listExpenses = expenseDao.findByUserIdAndDateBetween(userId, initialDate, endDate);
        return listExpenses.stream().map(ExpenseAmount::getExpenseAmount).reduce(0, Integer::sum);
    }


    @Override
    public Map<String, Long> getExpensesByCategory(int userId, LocalDate initialDate, LocalDate endData) {
        List<TotalExpense> list = expenseDao.getExpensesByCategory(userId, initialDate, endData);
        Map<String, Long> map = new HashMap<>();
        for (TotalExpense e : list) {
            map.put(e.getCategoryName(), e.getTotal());
        }
        return map;
    }
}
