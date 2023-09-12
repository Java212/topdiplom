package ru.top.java212.model;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    private String title;


    private String linkToTheImage;

    private String specification;

    private BigDecimal price;

    public Product() {
    }

    public Product(Integer id, Category category, Address address, String title, String linkToTheImage, String specification, BigDecimal price) {
        this.id = id;
        this.category = category;
        this.address = address;
        this.title = title;
        this.linkToTheImage = linkToTheImage;
        this.specification = specification;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategories() {
        return category;
    }

    public void setCategories(Category category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkToTheImage() {
        return linkToTheImage;
    }

    public void setLinkToTheImage(String linkToTheImage) {
        this.linkToTheImage = linkToTheImage;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
