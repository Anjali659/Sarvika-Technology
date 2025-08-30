package com.sarvika.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRequestDTO {

    private int userId;
    private List<String> productIds;
    private int quantity;
    private double totalprice;
    private OrderStatus status;
    private LocalDateTime orderDate;
    public OrderRequestDTO(){}
    public OrderRequestDTO(int userId, List<String> productIds, int quantity, double totalprice, OrderStatus status,LocalDateTime orderDate) {
        this.userId = userId;
        this.productIds = productIds;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.status = status;
        this.orderDate = orderDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductId(List<String> productIds) {
        this.productIds = productIds;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}

