package ru.top.java212.calculationExpensesAndIncomesUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllIncomesUser;

import java.time.LocalDate;

@Component
public class CalculationAllIncomesUser implements AllIncomesUser {

    private EntityManager entityManager;

    public CalculationAllIncomesUser(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public int calculationIncomesUser(int userId, LocalDate initialPeriod, LocalDate endPeriod){
        TypedQuery<Integer> query = entityManager.createNamedQuery("selectAllIncomesUser", Integer.class);
        query.setParameter("userId", userId);
        query.setParameter("startData", initialPeriod);
        query.setParameter("endData", endPeriod);
        return query.getResultList().stream().mapToInt(Integer ::intValue).sum();
    }

    //todo сделать расчет доходов пользователя по источникам
}
