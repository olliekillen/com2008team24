import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                final ProductPage window = new ProductPage();
                window.initPanel();
                window.initFrame();
            }
        });
    }
}