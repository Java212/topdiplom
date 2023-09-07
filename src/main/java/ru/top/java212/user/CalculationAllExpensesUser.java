package ru.top.java212.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CalculationAllExpensesUser implements AllExpensesUser {
    private final EntityManager entityManager;

    public CalculationAllExpensesUser(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int calculationExpensesUser(int userId, LocalDate initialDate, LocalDate endData) {
        TypedQuery<Integer> query = entityManager.createNamedQuery("selectAllExpensesUser", Integer.class);
        query.setParameter("startData", initialDate);
        query.setParameter("endData", endData);
        query.setParameter("userId", userId);
        List<Integer> listExpenses = query.getResultList();
        return listExpenses.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public Map<String, Long> getExpensesByCategory(int userId, LocalDate initialDate, LocalDate endData) {
        TypedQuery<Tuple> query = entityManager.createQuery("""
                select expenseCategory.nameExpenseCategory as category, sum(expenseAmount) as total 
                  from Expense where date between :startData and :endData and user.id = :userId
                  group by expenseCategory.nameExpenseCategory                
                 """, Tuple.class);
        query.setParameter("startData", initialDate);
        query.setParameter("endData", endData);
        query.setParameter("userId", userId);
        return query.getResultList()
                .stream()
                .collect(
                        Collectors.toMap(
                                tuple -> ((String) tuple.get("category")),
                                tuple -> ((Number) tuple.get("total")).longValue()
                        )
                );
    }
}
