package ru.top.java212.user;

import java.time.LocalDate;

public interface ExpensesUser {

    int calculationExpenses(String name, LocalDate initialPeriod, LocalDate endPeriod);
}
