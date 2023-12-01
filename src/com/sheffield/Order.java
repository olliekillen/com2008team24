package com.sheffield;

/**
 * This class represents an order object
 *
 * @author Oliver Killen
 */
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
            this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(int orderNumber) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Float getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Float orderCost) {
        this.orderCost = orderCost;
    }

    public Boolean getOrderBlocked() {
        return orderBlocked;
    }

    public void setOrderBlocked(Boolean orderBlocked) {
        this.orderBlocked = orderBlocked;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
