package com.sheffield;

import com.sheffield.Products.Product;

import java.math.BigDecimal;

public class RollingStock extends Product {

    private String historicalEra;

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
        return historicalEra != null && historicalEra.length() <= 7;
    }

}
