package ru.top.java212.entity;

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
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Order> orders;
    private String title;
    private String linkToTheImage;
    private String specification;
    private BigDecimal price;
    private boolean isBusy;

    public Product() {
    }

    public Product(Category category, String title, String linkToTheImage, String specification, BigDecimal price) {
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
