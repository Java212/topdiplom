package ru.top.java212.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.IncomeCategory;
import java.util.List;

@Repository
public interface IncomeCategoryRepository extends CrudRepository<IncomeCategory, Integer> {

    @Query(value = "select income_categories.id, income_categories.name from income_categories right join incomes ON income_categories.id = incomes.category_id", nativeQuery = true)
    List<IncomeCategory> getUsedCategories();
}
