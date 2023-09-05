package ru.top.java212;

import jakarta.persistence.NamedQuery;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.ExpenseCategory;

@NamedQuery(
        name = "select", query = ""
)
public interface ExpenseCategoryDbDao extends CrudRepository<ExpenseCategory, Integer> {

}
