package ru.top.java212.dao;

import java.time.LocalDate;

public interface AllIncomesUser {

    int calculationIncomesUser(int userId, LocalDate initialPeriod, LocalDate endPeriod);
}
