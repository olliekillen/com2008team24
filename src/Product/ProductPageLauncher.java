package Product;
import javax.swing.*;
import java.awt.*;

public class ProductPageLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                final ProductPageUI window = new ProductPageUI();
                window.initPanel();
                window.initFrame();
            }
        });
    }
}