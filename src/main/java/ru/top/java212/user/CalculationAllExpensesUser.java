package ru.top.java212.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CalculationAllExpensesUser implements AllExpensesUser{
    private final EntityManager entityManager;

    public CalculationAllExpensesUser(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int calculationExpensesUser(int userId, LocalDate initalDate, LocalDate endData){
        TypedQuery<Integer> query = entityManager.createNamedQuery("selectAllExpensesUser", Integer.class);
        query.setParameter("startData", initalDate);
        query.setParameter("endData", endData);
        query.setParameter("userId", userId);
        List<Integer> listExpenses = query.getResultList();
        return listExpenses.stream().mapToInt(Integer::intValue).sum();
    }
}
