package com.sheffield;

import com.sheffield.Products.Product;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class IndividualProductPageUI extends JFrame {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    JPanel singleProductPagePanel = new JPanel(null);

    JLabel singleProductHeader = new JLabel();
    JButton singleProductAccountButton = new JButton();
    JButton singleProductBasketButton = new JButton();
    JButton singleProductStaffPageButton = new JButton();
    JLabel singleProductSidebar = new JLabel();
    JLabel singleProductName = new JLabel();
    JLabel singleProductPrice = new JLabel();
    JLabel singleProductCode = new JLabel();
    JLabel singleProductBrand = new JLabel();
    JLabel singleProductScale = new JLabel();
    JLabel singleProductEra = new JLabel();
    JLabel singleProductBracket = new JLabel();
    JButton singleProductAddBasket= new JButton();
    JButton singleProductOrder= new JButton();
    JButton singleProductBrowse= new JButton();
    JLabel singleProductBox = new JLabel();
    JPanel singleProductAreaBorder = new JPanel(null);
    JLabel singleProductBackground = new JLabel();

    public void initFrame(Product product)
    {
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((Math.round(xSize)),9000);
        initPanel(product);
        this.add(singleProductPagePanel);
        this.setVisible(true);
    }

    public void initPanel(Product product)
    {
        /* For colours of each of the components:
         * Purple: 11854529
         * Red: 2743738
         * Light Green: 8741250
         * Dark Green: 14995422
         * Blue: 15440650
         * White: 1
         * Black: Don't enter anything (default).
         * Transparent?: 15658734
         */

        singleProductHeader.setLocation(0,0);
        singleProductHeader.setSize((Math.round(xSize)),70);
        singleProductHeader.setForeground( new Color(-1) );
        singleProductHeader.setFont(new Font("Merryweather", Font.BOLD, 50));
        singleProductHeader.setOpaque(true);
        singleProductHeader.setBackground( new Color(-11854529) );
        singleProductHeader.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        singleProductHeader.setText("Trains of Sheffield");
        singleProductHeader.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductPagePanel.add(singleProductHeader);

        singleProductAccountButton.setLocation(0,70);
        singleProductAccountButton.setSize((int) (Math.round(xSize * 0.16)),87);
        singleProductAccountButton.setForeground( new Color(-1) );
        singleProductAccountButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        singleProductAccountButton.addActionListener(e-> {
            try {
                singleProductAccountButton_Click();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        singleProductAccountButton.setBackground( new Color(-2743738) );
        singleProductAccountButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        singleProductAccountButton.setText("   Account");
        singleProductAccountButton.setHorizontalAlignment(SwingConstants.LEFT);
        singleProductPagePanel.add(singleProductAccountButton);

        singleProductBasketButton.setLocation(0,157);
        singleProductBasketButton.setSize((int) (Math.round(xSize * 0.16)),87);
        singleProductBasketButton.setForeground( new Color(-1) );
        singleProductBasketButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        singleProductBasketButton.addActionListener(e->singleProductBasketButton_Click());
        singleProductBasketButton.setBackground( new Color(-2743738) );
        singleProductBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        singleProductBasketButton.setText("   Basket");
        singleProductBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
        singleProductPagePanel.add(singleProductBasketButton);

        singleProductStaffPageButton.setLocation(0,244);
        singleProductStaffPageButton.setSize((int) (Math.round(xSize * 0.16)),87);
        singleProductStaffPageButton.setForeground( new Color(-1) );
        singleProductStaffPageButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        singleProductStaffPageButton.addActionListener(e->singleProductStaffPageButton_Click());
        singleProductStaffPageButton.setBackground( new Color(-15440650) );
        singleProductStaffPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        singleProductStaffPageButton.setText("   To Staff Page");
        singleProductStaffPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        singleProductPagePanel.add(singleProductStaffPageButton);

        singleProductSidebar.setLocation(0,70);
        singleProductSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
        singleProductSidebar.setOpaque(true);
        singleProductSidebar.setBackground( new Color(-11854529) );
        singleProductPagePanel.add(singleProductSidebar);

        singleProductName.setLocation((int) (Math.round(xSize * 0.19)), (int) (Math.round(ySize * 0.11)));
        singleProductName.setSize((int) (Math.round(xSize * 0.75)), 80);
        singleProductName.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductName.setForeground( new Color(-1) );
        singleProductName.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductPrice.setLocation((int) (Math.round(xSize * 0.19)), (int) (Math.round(ySize * 0.22)));
        singleProductPrice.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductPrice.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductPrice.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductCode.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.3)));
        singleProductCode.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductCode.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductCode.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductBrand.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.38)));
        singleProductBrand.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductBrand.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductBrand.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductScale.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.46)));
        singleProductScale.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductScale.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductScale.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductEra.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.54)));
        singleProductEra.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductEra.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductEra.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductBracket.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.62)));
        singleProductBracket.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductBracket.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductBracket.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductAddBasket.setLocation((int) (Math.round(xSize * 0.28)),  (int) (Math.round(ySize * 0.73)));
        singleProductAddBasket.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.1 )));
        singleProductAddBasket.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductAddBasket.setForeground( new Color(-1) );
        singleProductAddBasket.setFont(new Font("Merriweather", Font.BOLD, 18));
        singleProductAddBasket.addActionListener(e -> singleProductAddBasket_Click());
        singleProductAddBasket.setBackground( new Color(-2743738) );
        singleProductAddBasket.setText("Add To Basket");
        singleProductPagePanel.add(singleProductAddBasket);

        singleProductOrder.setLocation((int) (Math.round(xSize * 0.48)),  (int) (Math.round(ySize * 0.73)));
        singleProductOrder.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.1 )));
        singleProductOrder.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductOrder.setForeground( new Color(-1) );
        singleProductOrder.setFont(new Font("Merriweather", Font.BOLD, 18));
        singleProductOrder.addActionListener(e -> singleProductOrder_Click());
        singleProductOrder.setBackground( new Color(-2743738) );
        singleProductOrder.setText("View Order");
        singleProductPagePanel.add(singleProductOrder);

        singleProductBrowse.setLocation((int) (Math.round(xSize * 0.68)),  (int) (Math.round(ySize * 0.73)));
        singleProductBrowse.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.1 )));
        singleProductBrowse.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductBrowse.setForeground( new Color(-1) );
        singleProductBrowse.setFont(new Font("Merriweather", Font.BOLD, 18));
        singleProductBrowse.addActionListener(e -> singleProductBrowse_Click());
        singleProductBrowse.setBackground( new Color(-2743738) );
        singleProductBrowse.setText("Continue Browsing");
        singleProductPagePanel.add(singleProductBrowse);

        singleProductBox.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.22)));
        singleProductBox.setSize((int) (Math.round(xSize * 0.73)),464);
        singleProductBox.setOpaque(true);
        singleProductBox.setBackground( new Color(-1) );
        singleProductPagePanel.add(singleProductBox);

        singleProductAreaBorder.setLocation((int) (Math.round(xSize * 0.19)),(int) (Math.round(ySize * 0.12)));
        singleProductAreaBorder.setSize((int) (Math.round(xSize * 0.75)),(int) (Math.round(ySize * 0.75)));
        singleProductAreaBorder.setOpaque(true);
        singleProductAreaBorder.setBackground( new Color(-14995422) );
        singleProductPagePanel.add(singleProductAreaBorder);

        singleProductBackground.setLocation(0,0);
        singleProductBackground.setSize((Math.round(xSize)),9000);
        singleProductBackground.setOpaque(true);
        singleProductBackground.setBackground( new Color(-8741250) );
        singleProductPagePanel.add(singleProductBackground);

        singleProductPagePanel.setVisible(true);
    }

    public void initProductDetails(Product product) {
        singleProductName.setText(product.getProductName());
        singleProductPagePanel.add(singleProductName);
        singleProductPrice.setText("Price: £" + product.getRetailPrice());
        singleProductPagePanel.add(singleProductPrice);
        singleProductCode.setText("Product Code: " + product.getProductCode());
        singleProductPagePanel.add(singleProductCode);
        singleProductBrand.setText("Brand: " + product.getBrandName());
        singleProductPagePanel.add(singleProductBrand);
        singleProductScale.setText("Modelling Scale: " + product.getModellingScale());
        singleProductPagePanel.add(singleProductScale);
        if (product.getProductCode().charAt(0) == 'L') {
            Locomotive locomotive = new Locomotive();
            if (product instanceof  Locomotive) { locomotive = (Locomotive) product; }
            singleProductEra.setText("Historical Era: " + locomotive.getHistoricalEra());
            singleProductPagePanel.add(singleProductEra);
            singleProductBracket.setText("Price Bracket: " + locomotive.getPriceBracket());
            singleProductPagePanel.add(singleProductBracket);
        } else if (product.getProductCode().charAt(0) == 'S') {
            RollingStock rollingStock = new RollingStock();
            if (product instanceof  RollingStock) { rollingStock = (RollingStock) product; }
            singleProductEra.setText("Historical Era: " + rollingStock.getHistoricalEra());
            singleProductPagePanel.add(singleProductEra);
        } else if (product.getProductCode().charAt(0) == 'C') {
            Controller controller = new Controller();
            if (product instanceof  Controller) { controller = (Controller) product; }
            if(controller.getIsDigital()) {
                singleProductEra.setText("Digital");
                singleProductPagePanel.add(singleProductEra);
            } else {
                singleProductEra.setText("Not Digital");
                singleProductPagePanel.add(singleProductEra);
            }
        }
    }

    public void singleProductAccountButton_Click() throws SQLException
    {
        //takes the user to the account page
        this.dispose();
        AccountPage accountPage = new AccountPage();
        DatabaseConnectionHandler con = new DatabaseConnectionHandler();
        con.openConnection();
        accountPage.initPanel(1, con.getConnection()); // TODO USER ID
        con.closeConnection();
    }
    public void singleProductBasketButton_Click()
    { System.out.println("singleProductBasketButton_Click() has been pressed "); }
    public void singleProductStaffPageButton_Click() { System.out.println("Placeholder"); }
    public void singleProductAddBasket_Click() {}
    public void singleProductOrder_Click() {}
    public void singleProductBrowse_Click() {
        this.dispose();
        ProductPageUI productPage = new ProductPageUI();
        productPage.initFrame();
    }
}
