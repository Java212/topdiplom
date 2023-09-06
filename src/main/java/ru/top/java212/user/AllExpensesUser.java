package ru.top.java212.user;


import java.time.LocalDate;

public interface AllExpensesUser {

    int calculationExpensesUser(int userId, LocalDate initalDate, LocalDate endData);
}
