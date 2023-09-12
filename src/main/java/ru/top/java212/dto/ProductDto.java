package ru.top.java212.dto;



import java.math.BigDecimal;


public class ProductDto {
    private Integer id;
    private String title;
    private String linkToTheImage;
    private String category;
    private String specification;
    private BigDecimal price;

    public ProductDto(){}

    public ProductDto(String title, String category, String specification,String linkToTheImage, BigDecimal price) {
        this.linkToTheImage = linkToTheImage;
        this.title = title;
        this.category = category;
        this.specification = specification;
        this.price = price;
    }

    public String getLinkToTheImage() {
        return linkToTheImage;
    }

    public void setLinkToTheImage(String linkToTheImage) {
        this.linkToTheImage = linkToTheImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

