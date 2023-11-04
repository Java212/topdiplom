package ru.top.java212.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.Income;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Integer> {

    @Query(value = "SELECT SUM(summ) FROM incomes", nativeQuery = true)
    Integer getAllIncomesSumm();
}
