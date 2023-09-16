package ru.top.java212.calculationExpensesAndIncomesFamily;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllIncomesFamily;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//todo переделать класс по образцу CalculationAllExpensesFamily

@Component
public class CalculationAllIncomesFamily implements AllIncomesFamily {

        private final EntityManager entityManager;

        public CalculationAllIncomesFamily(EntityManager entityManager) {
                this.entityManager = entityManager;
        }

        @Override
        public int calculationIncomesFamily(LocalDate initalDate, LocalDate endData){
        TypedQuery<Integer> queryInc = entityManager.createNamedQuery("selectAllIncomesFamily", Integer.class);
        queryInc.setParameter("startData", initalDate);
        queryInc.setParameter("endData", endData);
        List<Integer> listExpenses = queryInc.getResultList();
        return listExpenses.stream().mapToInt(Integer::intValue).sum();
}
        @Override
        public Map<String, Long> getIncomesFamilyBySource(LocalDate initalDate, LocalDate endData){
                TypedQuery<Tuple> query = entityManager.createQuery("""
                        select incomeCategory.sourceIncomeCategory as category, sum(incomeAmount) as total
                        from Income where date between : startDate and : endDate
                        group by incomeCategory.sourceIncomeCategory""", Tuple.class);
                query.setParameter("startDate", initalDate);
                query.setParameter("endDate", endData);
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
