package com.josedacruz.learning.spring.backend_server.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private Integer id;
    private int userId;
    private Integer entityId; // Nullable
    private int categoryId;
    private LocalDate date;
    private BigDecimal amount;
    private String description;
    private String type; // INCOME or EXPENSE
    private String paymentMethod;

    // Constructors
    public Transaction() {}

    public Transaction(Integer id, int userId, Integer entityId, int categoryId, LocalDate date,
                       BigDecimal amount, String description, String type, String paymentMethod) {
        this.id = id;
        this.userId = userId;
        this.entityId = entityId;
        this.categoryId = categoryId;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.paymentMethod = paymentMethod;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", entityId=" + entityId +
                ", categoryId=" + categoryId +
                ", date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
