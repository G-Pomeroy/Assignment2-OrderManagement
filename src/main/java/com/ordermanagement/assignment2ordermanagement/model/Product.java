package com.ordermanagement.assignment2ordermanagement.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private int categoryId;
    private String imageUrl;
    private int reviewId;
    private String name;
    private BigDecimal cost;
    private String description;
    private int stock;

    //Constructors
    //region

    public Product(int productId, int categoryId, String data, int reviewId, String name, BigDecimal cost,
                   String description, int stock) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.imageUrl = data;
        this.reviewId = reviewId;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.stock = stock;
    }

    //endregion

    //Getters and Setters
    //region
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //endregion
}