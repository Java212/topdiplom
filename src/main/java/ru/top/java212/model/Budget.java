package ru.top.java212.model;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="budget_users")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="budget_id")
    private Integer id;

    @Column(name="number_account_user")
    private String numberAccount;

    @Column(name="current_balance_user")
    private int currentBalance;

    @OneToMany(mappedBy = "budget")
    List<User> users = new LinkedList<>();

    public Budget(Integer id, String numberAccount, int currentBalance) {
        this.id = id;
        this.numberAccount = numberAccount;
        this.currentBalance = currentBalance;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumberAccount() {
        return numberAccount;
    }
    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }
    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

}
