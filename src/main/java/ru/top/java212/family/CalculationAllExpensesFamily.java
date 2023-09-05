package ru.top.java212.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.top.java212.ExpenseDbDao;
import ru.top.java212.model.Expense;

import java.time.LocalDate;
import java.util.List;

@Component

public class CalculationAllExpensesFamily  {

@Autowired
    ExpenseDbDao expenseDbDao;

public int calculationExpensesFamily(LocalDate initalDate, LocalDate endData){
        List<Expense> listAllExpensesFamily = (List<Expense>) expenseDbDao.findAll();
        return listAllExpensesFamily.stream().filter(exp->{
        if(exp.getDate()>initalDate && exp.getDate()<endData) {
        return exp;
        }
        }).mapToInt(Expense::getExpenseAmount).sum();
}
}
