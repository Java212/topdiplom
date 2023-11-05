package ru.top.java212.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.Expence;

@Repository
public interface ExpenceRepository extends CrudRepository<Expence, Integer> {

    @Query(value = "SELECT SUM(summ) FROM expences", nativeQuery = true)
    Double getAllExpencesSumm();

    @Query(value = "SELECT SUM(summ) FROM expences WHERE user_id = :id", nativeQuery = true)
    Double getAllExpencesSummByUserId(@Param("id") Integer id);
}
