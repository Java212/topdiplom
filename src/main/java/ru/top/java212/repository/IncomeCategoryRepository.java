package ru.top.java212.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.IncomeCategory;

@Repository
public interface IncomeCategoryRepository extends CrudRepository<IncomeCategory, Integer> {}
