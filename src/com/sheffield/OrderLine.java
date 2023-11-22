package com.sheffield;

public class OrderLine {
    String productCode;
    Integer orderLineNumber;
    Integer orderNumber;
    Integer quantity;
    Double lineCost;

    public OrderLine(String code, Integer lineNum, Integer orderNum, Integer quant, Double cost) {
        productCode = code;
        orderLineNumber = lineNum;
        orderNumber = orderNum;
        quantity = quant;
        lineCost = cost;
    }
}
