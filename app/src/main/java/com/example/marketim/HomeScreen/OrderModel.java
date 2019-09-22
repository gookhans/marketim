package com.example.marketim.HomeScreen;

import java.io.Serializable;

public class OrderModel implements Serializable {
    private int date, month;
    private String marketName, orderName, productState;
    private double productPrice;
    private ProductDetail productDetail;

    public OrderModel() {

    }

    public int getDay() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    public String getProductState() {
        return productState;
    }

    public String getMarketName() {
        return marketName;
    }

    public String getOrderName() {
        return orderName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getOrderDetail() {
        return productDetail.getOrderDetail();
    }

    public Double getSummaryPrice() {
        return productDetail.getSummaryPrice();
    }
}

class ProductDetail implements Serializable {
    private String orderDetail;
    private double summaryPrice;

    public String getOrderDetail() {
        return orderDetail;
    }

    public Double getSummaryPrice() {
        return summaryPrice;
    }
}

