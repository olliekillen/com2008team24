import java.math.BigDecimal;

public class Track extends Product {

    public Track(String productCode, String brandName, String productName, BigDecimal retailPrice,
                    String modellingScale, Integer stockCount) {
        super(productCode, brandName, productName, retailPrice, modellingScale, stockCount);
    }

    @Override
    public String toString() { return super.toString(); }

}
