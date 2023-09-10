package ru.top.java212.dao;


import java.time.LocalDate;
import java.util.Map;

public interface AllExpensesUser {

    int calculationExpensesUser(int userId, LocalDate initialDate, LocalDate endData);
    Map<String, Long> getExpensesByCategory(int userId, LocalDate initialDate, LocalDate endData);
}
