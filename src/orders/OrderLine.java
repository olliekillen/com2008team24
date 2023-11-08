package orders;

public class OrderLine {
    String productCode;
    Integer orderLineNumber;
    Integer orderNumber;

    public OrderLine(String code, Integer lineNum, Integer orderNum) {
        productCode = code;
        orderLineNumber = lineNum;
        orderNumber = orderNum;
    }
}
