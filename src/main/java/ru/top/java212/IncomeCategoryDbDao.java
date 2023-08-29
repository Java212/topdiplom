package ru.top.java212;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.IncomeCategory;

public interface IncomeCategoryDbDao extends CrudRepository<IncomeCategory, Integer> {
}
