package ru.top.java212.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.ExpenceCategory;
import java.util.List;

@Repository
public interface ExpenceCategoryRepository extends CrudRepository<ExpenceCategory, Integer> {

    @Query(value = "select expence_categories.id, expence_categories.name from expence_categories right join expences ON expence_categories.id = expences.category_id", nativeQuery = true)
    List<ExpenceCategory> getUsedCategories();
}
