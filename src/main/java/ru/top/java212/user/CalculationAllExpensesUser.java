package ru.top.java212.user;

import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class CalculationAllExpensesUser implements AllExpensesUser{
    private final EntityManager entityManager;

    public CalculationAllExpensesUser(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int calculationExpensesUser(int userId, LocalDate initalDate, LocalDate endData){

        return 0;
    }
}
