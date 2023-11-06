package ru.top.java212.entity;

import jakarta.persistence.*;


import java.time.LocalDate;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private LocalDate startDate;
    private LocalDate endDate;

    public Order() {
    }

    public Order(Profile profile, Product product, LocalDate startDate, LocalDate endDate) {
        this.profile = profile;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getUser() {
        return profile;
    }

    public void setUser(User user) {
        this.profile = profile;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
