package ru.top.java212.model;

import jakarta.persistence.*;

@Entity
@Table (name="transactions_users")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="transaction_id")
    private Integer id;


}
