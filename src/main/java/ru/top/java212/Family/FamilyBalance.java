package ru.top.java212.Family;

import java.time.LocalDate;

public interface FamilyBalance {

    int getBalance(LocalDate initialPeriod, LocalDate endPeriod);

}
