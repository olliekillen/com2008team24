package com.sheffield;

import com.sheffield.Products.Product;

import java.math.BigDecimal;

/**
 * This class represents a locomotive object - it has two more attributes than the normal product object, determining
 * the era the locomotive is from and its price bracket (extra features, higher price)
 *
 * @author Luke Parry
 */
public class Locomotive extends Product {

    private String historicalEra;
    private String priceBracket;

    public Locomotive() {
        super();
        this.setHistoricalEra("Era 3");
        this.setPriceBracket("DCC-Ready");
    }

    public Locomotive(String productCode, String brandName, String productName, BigDecimal retailPrice,
    String modellingScale, Integer stockCount, String historicalEra, String priceBracket) {
        super(productCode, brandName, productName, retailPrice, modellingScale, stockCount);
        this.setHistoricalEra(historicalEra);
        this.setPriceBracket(priceBracket);
    }

    @Override
    public String toString() {
        return (productCode + " " + brandName + " " + productName + " " + retailPrice + " " + modellingScale + " "
        + stockCount + " " + historicalEra + " " + priceBracket);
    }

    public String getHistoricalEra() {
        return historicalEra;
    }

    public void setHistoricalEra(String historicalEra) {
        if (isValidHistoricalEra(historicalEra)) {
            this.historicalEra = historicalEra;
        } else { throw new IllegalArgumentException("Historical era is not valid."); }
    }

    public String getPriceBracket() {
        return priceBracket;
    }

    public void setPriceBracket(String priceBracket) {
        if (isValidPriceBracket(priceBracket)) {
            this.priceBracket = priceBracket;
        } else { throw new IllegalArgumentException("Price bracket is not valid."); }
    }

    private boolean isValidHistoricalEra(String historicalEra) {
        return historicalEra != null && historicalEra.length() <= 8;
    }

    private boolean isValidPriceBracket(String priceBracket) {
        return priceBracket != null && priceBracket.length() <= 10;
    }
}
