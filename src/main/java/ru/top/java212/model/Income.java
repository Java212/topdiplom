package ru.top.java212.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "incomes")

public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "income _id")
    private Integer id;

    @Column(name = "expense_amount")
    private int incomeAmount;

    @Column(name = "date_expense")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Income(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(int incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
