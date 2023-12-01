package com.sheffield;

import com.sheffield.Products.Product;

import java.math.BigDecimal;

/**
 * This class represents a track object
 *
 * @author Luke Parry
 */
public class Track extends Product {

    public Track(String productCode, String brandName, String productName, BigDecimal retailPrice,
                    String modellingScale, Integer stockCount) {
        super(productCode, brandName, productName, retailPrice, modellingScale, stockCount);
    }

    @Override
    public String toString() { return super.toString(); }

}
