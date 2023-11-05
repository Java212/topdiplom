package ru.top.java212.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.Income;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Integer> {

    @Query(value = "SELECT SUM(summ) FROM incomes", nativeQuery = true)
    Double getAllIncomesSumm();

    @Query(value = "SELECT SUM(summ) FROM incomes WHERE user_id = :id", nativeQuery = true)
    Double getAllIncomesSummByUserId(@Param("id") Integer id);
}
