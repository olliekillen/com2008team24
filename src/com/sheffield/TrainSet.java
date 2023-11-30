package com.sheffield;

import com.sheffield.Products.Product;

import java.math.BigDecimal;

/**
 * This class represents a train set object
 *
 * @author Luke Parry
 */
public class TrainSet extends Product {

    public TrainSet(String productCode, String brandName, String productName, BigDecimal retailPrice,
    String modellingScale, Integer stockCount) {
        super(productCode, brandName, productName, retailPrice, modellingScale, stockCount);
    }

    @Override
    public String toString() { return super.toString(); }
}
