package ru.top.java212.model;

import jakarta.persistence.*;

@Entity
@Table(name="incomes_category")

public class IncomeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_category_id")
    private Integer id;

    @Column(name = "source_income_category")
    private String sourceIncomeCategory;

    IncomeCategory() {
    }

    public IncomeCategory(String sourceIncomeCategory) {
        this.sourceIncomeCategory = sourceIncomeCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceIncomeCategory() {
        return sourceIncomeCategory;
    }

    public void setSourceIncomeCategory(String sourceIncomeCategory) {
        this.sourceIncomeCategory = sourceIncomeCategory;
    }
}
