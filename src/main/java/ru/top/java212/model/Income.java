package ru.top.java212.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "income_id")
    private  Integer id;

    @Column(name = "source_income")
    private String sourceIncome;

    @Column(name = "amount_income")
    private int amountIncome;

    @Column(name = "date_income")
    private LocalDate date;

    @ManyToMany(mappedBy = "incomes_user", fetch = FetchType.LAZY)
    private Set<User> users;

    public Income(Integer id, String sourceIncome, int amountIncome, LocalDate date) {
        this.id = id;
        this.sourceIncome = sourceIncome;
        this.amountIncome = amountIncome;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceIncome() {
        return sourceIncome;
    }

    public void setSourceIncome(String sourceIncome) {
        this.sourceIncome = sourceIncome;
    }

    public int getAmountIncome() {
        return amountIncome;
    }

    public void setAmountIncome(int amountIncome) {
        this.amountIncome = amountIncome;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
