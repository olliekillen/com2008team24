package com.sheffield;

public class Order {
    Integer orderNumber;
    String orderDate;
    String orderStatus;
    Float orderCost;
    Boolean orderBlocked;
    String dateBlocked;
    Integer userId;

    public Order(Integer number, String date, String status, Float cost, Boolean blocked, String blockedDate, Integer user) {
        orderNumber = number;
        orderDate = date;
        orderStatus = status;
        orderCost = cost;
        orderBlocked = blocked;
        dateBlocked = blockedDate;
        userId = user;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        //if (isValidHistoricalEra(historicalEra)) {
            this.orderNumber = orderNumber;
        //} else { throw new IllegalArgumentException("Historical era is not valid."); }
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(int orderNumber) {
        //if (isValidHistoricalEra(historicalEra)) {
        this.orderDate = orderDate;
        //} else { throw new IllegalArgumentException("Historical era is not valid."); }
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        //if (isValidHistoricalEra(historicalEra)) {
        this.orderStatus = orderStatus;
        //} else { throw new IllegalArgumentException("Historical era is not valid."); }
    }

    public Float getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Float orderCost) {
        //if (isValidHistoricalEra(historicalEra)) {
        this.orderCost = orderCost;
        //} else { throw new IllegalArgumentException("Historical era is not valid."); }
    }

    public Boolean getOrderBlocked() {
        return orderBlocked;
    }

    public void setOrderBlocked(Boolean orderBlocked) {
        //if (isValidHistoricalEra(historicalEra)) {
        this.orderBlocked = orderBlocked;
        //} else { throw new IllegalArgumentException("Historical era is not valid."); }
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) {
        //if (isValidHistoricalEra(historicalEra)) {
        this.userId = userId;
        //} else { throw new IllegalArgumentException("Historical era is not valid."); }
    }
}
