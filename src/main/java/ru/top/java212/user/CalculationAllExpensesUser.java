package ru.top.java212.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class CalculationAllExpensesUser implements AllExpensesUser{
    private final EntityManager entityManager;

    public CalculationAllExpensesUser(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int calculationExpensesUser(int userId, LocalDate initalDate, LocalDate endData){
        TypedQuery<Integer> query = entityManager.createNamedQuery("selectAllExpensesFamily", Integer.class);
        query.setParameter("startData", initalDate);
        query.setParameter("endData", endData);
        List<Integer> listExpenses = query.getResultList();
        return listExpenses.stream().mapToInt(Integer::intValue).sum();
    }
}
