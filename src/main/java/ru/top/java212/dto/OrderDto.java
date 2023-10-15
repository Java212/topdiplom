package ru.top.java212.dto;

import ru.top.java212.model.UserInfo;

import java.time.LocalDate;

public class OrderDto {

    private Integer productId;
    private LocalDate startDate;
    private LocalDate endDate;
    private UserInfo userInfo;

    public OrderDto() {
    }

    public OrderDto(Integer productId, LocalDate startDate, LocalDate endDate) {
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
