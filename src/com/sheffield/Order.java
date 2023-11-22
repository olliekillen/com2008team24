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
}
