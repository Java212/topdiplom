package ru.top.java212.Family;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import ru.top.java212.RepositoryNamesCategoryExpenses;
import ru.top.java212.model.ExpenseCategory;

import java.time.LocalDate;
import java.util.*;


public class CalculationExpensesFamily implements DetailingExpensesFamily {
    @Autowired
    EntityManager entityManager;

    @Autowired
    RepositoryNamesCategoryExpenses namesCategoryExpenses;

    @Override
    public  Map<String, Integer> detailingExpensesFamily(LocalDate initialPeriod, LocalDate endPeriod){
        Map<String, Integer> listExpensesByCategory = new HashMap<>();

        TypedQuery<ExpenseCategory> query = entityManager.createNamedQuery("selectAllExpenseCategoryForPeriod", ExpenseCategory.class);
        query.setParameter("startPeriod", initialPeriod);
        query.setParameter("endPeriod", endPeriod);
        List<ExpenseCategory> listCategoryExp = query.getResultList();

        List<String> listCategory = namesCategoryExpenses.getListAllNamesCategoryExpenses();
        Iterator<String> iterator = listCategory.iterator();
        int i=0;
        while (iterator.hasNext()){
            String name = listCategory.get(i);
          int sum = listCategoryExp.stream().reduce(0,(a,b)->{
              if(a.getNameExpenseCategory()==name && b.getNameExpenseCategory().equals(name))
                  return a.getAmountExpenseCategory() + b.getAmountExpenseCategory();
          }, (a,b)->a+b);
            listExpensesByCategory.put(name, sum);
            i++;
        }
        return listExpensesByCategory;
    }
}
