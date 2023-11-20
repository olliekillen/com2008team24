package com.sheffield;

import com.sheffield.Products.Product;

import java.math.BigDecimal;

public class Controller extends Product {

    private boolean isDigital;

    public Controller() {
        super();
        this.setIsDigital(false);
    }

    public Controller(String productCode, String brandName, String productName, BigDecimal retailPrice,
                        String modellingScale, Integer stockCount, Boolean isDigital) {
        super(productCode, brandName, productName, retailPrice, modellingScale, stockCount);
        this.setIsDigital(isDigital);
    }

    @Override
    public String toString() {
        return (productCode + " " + brandName + " " + productName + " " + retailPrice + " " + modellingScale + " "
        + stockCount + " " + isDigital);
    }

    public boolean getIsDigital() {
        return isDigital;
    }

    public void setIsDigital(Boolean isDigital) {
        this.isDigital = isDigital;
    }

}
