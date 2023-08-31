package ru.top.java212.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tools")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    private double price;

    private Address address;

    @Column(name = "in_rent")
    private boolean inRent;

    public Tool(String name){
        this.name = name;
    }


}
