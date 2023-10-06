package ru.top.java212.calculationExpensesAndIncomesFamily;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllExpensesFamily;
import ru.top.java212.dao.ExpenseDbDao;
import ru.top.java212.model.ExpenseAmount;
import ru.top.java212.model.TotalExpense;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalculationAllExpensesFamily implements AllExpensesFamily {

     private final ExpenseDbDao expenseDbDao;

        @Autowired
        public CalculationAllExpensesFamily(ExpenseDbDao expenseDbDao) {
                this.expenseDbDao = expenseDbDao;
        }

        @Override
        public int calculationExpensesFamily(LocalDate initialDate, LocalDate endData){
             List<ExpenseAmount> list = expenseDbDao.findByDateBetween(initialDate, endData);
             return list.stream().mapToInt(ExpenseAmount::getExpenseAmount).reduce(0, Integer::sum);
        }

        @Override
        public Map<String, Long> calculationExpensesFamilyByCategory(LocalDate initialDate, LocalDate endData){
                List<TotalExpense> list = expenseDbDao.getExpensesFamilyByCategory(initialDate, endData);
                Map<String, Long> map = new HashMap<>();
                for (TotalExpense e : list) {
                        map.put(e.getCategoryName(), e.getTotal());
                }
                return map;
        }
}
