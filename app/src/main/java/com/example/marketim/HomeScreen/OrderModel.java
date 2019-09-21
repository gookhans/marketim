package com.example.marketim.HomeScreen;

import java.io.Serializable;

public class OrderModel implements Serializable {
    private int date, month, orderDetail;
    private String marketName, orderName, productState;
    private double productPrice, summaryPrice;
    private Object productDetail;


    public OrderModel() {

    }
    public int getDay(){
        return date;
    }
    public int getMonth(){
        return month;
    }
    public String getProductState(){
        return productState;
    }
    public String getMarketName(){
        return marketName;
    }
    public String getOrderName(){
        return orderName;
    }
    public double getProductPrice(){
        return productPrice;
    }
    public Object getProductDetail(){
        return productDetail;
    }
}

