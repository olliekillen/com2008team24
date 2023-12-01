package com.sheffield;

import com.sheffield.Products.Product;

import java.math.BigDecimal;

/**
 * This class represents a rolling stock object - it has one more attributes than the normal product object, determining
 * the era the rolling stock is from
 *
 * @author Luke Parry
 */
public class RollingStock extends Product {

    private String historicalEra;

    public RollingStock() {
        super();
        this.setHistoricalEra("Era 3");
    }

    public RollingStock(String productCode, String brandName, String productName, BigDecimal retailPrice,
                      String modellingScale, Integer stockCount, String historicalEra) {
        super(productCode, brandName, productName, retailPrice, modellingScale, stockCount);
        this.setHistoricalEra(historicalEra);
    }

    @Override
    public String toString() {
        return (productCode + " " + brandName + " " + productName + " " + retailPrice + " " + modellingScale + " "
                + stockCount + " " + historicalEra);
    }

    public String getHistoricalEra() {
        return historicalEra;
    }

    public void setHistoricalEra(String historicalEra) {
        if (isValidHistoricalEra(historicalEra)) {
            this.historicalEra = historicalEra;
        } else { throw new IllegalArgumentException("Historical era is not valid."); }
    }

    private boolean isValidHistoricalEra(String historicalEra) {
        return historicalEra != null && historicalEra.length() <= 8;
    }

}
