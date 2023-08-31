package ru.top.java212.dto;



import java.math.BigDecimal;


public class ProductDto {
    private Long id;
    private String title;
    private String category;
    private String specification;
    private BigDecimal price;

    public ProductDto(){}

    public ProductDto(Long id, String title, String category, String specification, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.specification = specification;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

