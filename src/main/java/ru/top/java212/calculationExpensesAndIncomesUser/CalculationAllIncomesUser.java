package ru.top.java212.calculationExpensesAndIncomesUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllIncomesUser;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Map<String, Long> getIncomesUserBySource(int userId, LocalDate initalDate, LocalDate endData){
        TypedQuery<Tuple> query = entityManager.createQuery("""
                        select incomeCategory.sourceIncomeCategory as category, sum(incomeAmount) as total
                        from Income where date between : startDate and : endDate and user.id =: userId
                        group by incomeCategory.sourceIncomeCategory""", Tuple.class);
        query.setParameter("startDate", initalDate);
        query.setParameter("endDate", endData);
        query.setParameter("userId", userId);
        return query.getResultList()
                .stream()
                .collect(
                        Collectors.toMap(
                                tuple -> ((String) tuple.get("category")),
                                tuple -> ((Number) tuple.get("total")).longValue()
                        )
                );
    }
}
