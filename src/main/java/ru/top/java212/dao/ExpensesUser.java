package ru.top.java212.dao;

import java.time.LocalDate;

public interface ExpensesUser {

    int calculationExpenses(String name, LocalDate initialPeriod, LocalDate endPeriod);
}
