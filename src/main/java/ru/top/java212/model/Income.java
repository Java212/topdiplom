package ru.top.java212.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "incomes")

@NamedQuery(
        name="selectAllIncomes", query = "select n.incomeAmount from Income n where n.date between : startData and : endData"
)

public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Integer id;

    @Column(name = "income_amount")
    private int incomeAmount;

    @Column(name = "date_income")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Income(){}

    public Income(int incomeAmount){
        this.incomeAmount=incomeAmount;
        this.date=LocalDate.now();
    }


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
