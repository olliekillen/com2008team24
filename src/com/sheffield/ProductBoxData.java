package com.sheffield;

import com.sheffield.Products.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductBoxData {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    public void initProductBoxData(ProductPageUI window, JComboBox<String> productTypeFilterCombo, int n,
    Boolean isSearch, String searchContents) {
        //chooses which product type to filter by
        ProductRetriever productRetriever = new ProductRetriever();
        List<Product> productList;
        if (! isSearch) { productList = productRetriever.getProductsFromDatabase(productTypeFilterCombo); }
        else { productList = productRetriever.searchProductsFromDatabase(productTypeFilterCombo, searchContents); }
        //up to six products are shown depending on what is currently stored in the database
        if (productList.size() > n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.01)), 56, productList.get(0)); }
        if (productList.size() > 1 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.255)), 56, productList.get(1)); }
        if (productList.size() > 2 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.5)), 56, productList.get(2)); }
        if (productList.size() > 3 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.01)), 261, productList.get(3)); }
        if (productList.size() > 4 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.255)), 261, productList.get(4)); }
        if (productList.size() > 5 + n*6) {
            window.productBoxConstructor((int) (Math.round(xSize * 0.5)), 261, productList.get(5)); }
    }
}
