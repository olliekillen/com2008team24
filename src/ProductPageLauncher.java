import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                try {
                    DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
                    DatabaseOperations dop = new DatabaseOperations();
                    dch.openConnection();
                    final ProductPage window = new ProductPage();
                    window.initPanel();
                    window.initFrame();
                    TrainSet trainSet = new TrainSet("M58392", "Peco",
                    "Eurostar Train Set", new BigDecimal(0.25),
                    "N Gauge", 12);
//                    TrainSet trainSet = new TrainSet("M444", "Hornby",
//                    "Mallard Record Breaker Train Set", new BigDecimal(225.00),
//                    "OO Gauge", 25);
                    //dop.insertTrainSet(trainSet, dch.getConnection());
                    dch.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}