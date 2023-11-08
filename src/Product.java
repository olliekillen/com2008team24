import java.math.BigDecimal;

public class Product {

    protected String productCode;
    protected String brandName;
    protected String productName;
    protected BigDecimal retailPrice;
    protected String modellingScale;
    protected Integer stockCount;

    public Product(String productCode, String brandName, String productName, BigDecimal retailPrice,
    String modellingScale, Integer stockCount) {
        this.setProductCode(productCode);
        this.setBrandName(brandName);
        this.setProductName(productName);
        this.setRetailPrice(retailPrice);
        this.setModellingScale(modellingScale);
        this.setStockCount(stockCount);
    }

    @Override
    public String toString() {
        return (productCode + " " + brandName + " " + productName + " " + retailPrice + " " + modellingScale + " "
        + stockCount);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        if (isValidProductCode(productCode)) {
            this.productCode = productCode;
        } else {
            throw new IllegalArgumentException("Product code is not valid.");
        }
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        if (isValidBrandName(brandName)) {
            this.brandName = brandName;
        } else {
            throw new IllegalArgumentException("Brand name is not valid.");
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (isValidProductName(productName)) {
            this.productName = productName;
        } else {
            throw new IllegalArgumentException("Product name is not valid.");
        }
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        if (isValidRetailPrice(retailPrice)) {
            this.retailPrice = retailPrice;
        } else {
            throw new IllegalArgumentException("Retail price is not valid.");
        }
    }

    public String getModellingScale() { return modellingScale; }

    public void setModellingScale(String modellingScale) {
        if (isValidModellingScale(modellingScale)) {
            this.modellingScale = modellingScale;
        } else {
            throw new IllegalArgumentException("Modelling scale is not valid.");
        }
    }

    public Integer getStockCount() { return stockCount; }

    public void setStockCount(Integer stockCount) {
        if (isValidStockCount(stockCount)) {
            this.stockCount = stockCount;
        } else {
            throw new IllegalArgumentException("Stock count is not valid.");
        }
    }

    private boolean isValidProductCode(String productCode) {
        return productCode != null && productCode.length() <= 6 && productCode.length() >= 4;
    }

    private boolean isValidBrandName(String brandName) {
        return brandName != null && brandName.length() <= 25;
    }

    private boolean isValidProductName(String productName) {
        return productName != null && productName.length() <= 40;
    }

    private boolean isValidRetailPrice(BigDecimal retailPrice) {
        return retailPrice != null && retailPrice.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean isValidModellingScale(String modellingScale) {
        return modellingScale != null && (modellingScale.equals("N Gauge") || modellingScale.equals("TT Gauge") ||
        modellingScale.equals("OO Gauge"));
    }

    private boolean isValidStockCount(Integer stockCount) {
        return stockCount != null && stockCount <= 10000 && stockCount >= 0;
    }
}
