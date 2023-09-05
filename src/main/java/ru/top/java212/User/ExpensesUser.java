package ru.top.java212.User;

import java.time.LocalDate;

public interface ExpensesUser {

    int calculationExpenses(String name, LocalDate initialPeriod, LocalDate endPeriod);
}
