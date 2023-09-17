package ru.top.java212.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.User;

import java.math.BigDecimal;

public interface FamilyStartingCapitalDbDao extends CrudRepository<User, Integer> {

    @Query("select sum(startingCapital) from User")
    BigDecimal getFamilyStartingCapital();
}
