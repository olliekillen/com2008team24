package com.sheffield;

import com.sheffield.Products.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class determines how to filter the data provided to the product box (if at all), then calls the product box
 * constructor to generate the box in the right place
 *
 * @author Luke Parry
 */
public class ProductBoxData {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    public void initProductBoxData(ProductPageUI window, JComboBox<String> productTypeFilterCombo, int n,
    String filterType, String searchContents) {
        //chooses which product type to filter by
        ProductRetriever productRetriever = new ProductRetriever();
        List<Product> productList = switch (filterType) {
            case "Search" -> productRetriever.searchProductsFromDatabase
            (productTypeFilterCombo, searchContents);
            case "Brand" -> productRetriever.getProductsFromDatabaseBrand
            (productTypeFilterCombo, searchContents);
            case "Price" -> productRetriever.getProductsFromDatabasePrice
            (productTypeFilterCombo, searchContents);
            case "Scale" -> productRetriever.getProductsFromDatabaseScale
            (productTypeFilterCombo, searchContents);
            default -> productRetriever.getProductsFromDatabase(productTypeFilterCombo);
        };
        //up to six products are shown depending on what is currently stored in the database
        if (productList.size() > n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.0776)), productList.get(0)); }
        if (productList.size() > 1 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.255)), (int) (Math.round(ySize * 0.0776)), productList.get(1)); }
        if (productList.size() > 2 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.0776)), productList.get(2)); }
        if (productList.size() > 3 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.362)), productList.get(3)); }
        if (productList.size() > 4 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.255)), (int) (Math.round(ySize * 0.362)), productList.get(4)); }
        if (productList.size() > 5 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.362)), productList.get(5)); }
    }
}
