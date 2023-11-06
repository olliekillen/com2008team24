import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Stack;

//break class down into smaller parts (it should not be 600 lines).

public class ProductPageUI extends JFrame {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    JPanel productPagePanel = new JPanel(null);
    JScrollPane productPageScrollPane = new JScrollPane(null);

    JLabel leftArrow = new JLabel();
    JLabel rightArrow = new JLabel();
    JLabel profilePicture = new JLabel();
    JLabel productHeader = new JLabel();
    JButton productAccountButton = new JButton();
    JButton productBasketButton = new JButton();
    JButton productStaffPageButton = new JButton();
    JLabel productSidebar = new JLabel();
    JLabel productTypeFilterLabel = new JLabel();
    JComboBox productTypeFilterCombo = new JComboBox();
    JLabel productBrandFilterLabel = new JLabel();
    JComboBox productBrandFilterCombo = new JComboBox();
    JLabel productPriceFilterLabel = new JLabel();
    JComboBox productPriceFilterCombo = new JComboBox();
    JLabel productScaleFilterLabel = new JLabel();
    JComboBox productScaleFilterCombo = new JComboBox();
    JTextField productSearch = new JTextField();
    JLabel productFilterBar =  new JLabel();
    JLabel productTrainSetsText = new JLabel();
    JLabel productTrainSetsPageCount = new JLabel();
    JLabel productTrainSets1 = new JLabel();
    JLabel productTrainSets1Name = new JLabel();
    JLabel productTrainSets1Price = new JLabel();
    JLabel productTrainSets1Code = new JLabel();
    JLabel productTrainSets1Brand = new JLabel();
    JLabel productTrainSets1Scale = new JLabel();
    JButton productTrainSets1Info = new JButton();
    JButton productTrainSets1Cart = new JButton();
    JLabel productTrainSets2 = new JLabel();
    JLabel productTrainSets2Name = new JLabel();
    JLabel productTrainSets2Price = new JLabel();
    JLabel productTrainSets2Code = new JLabel();
    JLabel productTrainSets2Brand = new JLabel();
    JLabel productTrainSets2Scale = new JLabel();
    JButton productTrainSets2Info = new JButton();
    JButton productTrainSets2Cart = new JButton();
    JLabel productTrainSets3 = new JLabel();
    JLabel productTrainSets3Name = new JLabel();
    JLabel productTrainSets3Price = new JLabel();
    JLabel productTrainSets3Code = new JLabel();
    JLabel productTrainSets3Brand = new JLabel();
    JLabel productTrainSets3Scale = new JLabel();
    JButton productTrainSets3Info = new JButton();
    JButton productTrainSets3Cart = new JButton();
    JLabel productTrainSets4 = new JLabel();
    JLabel productTrainSets4Name = new JLabel();
    JLabel productTrainSets4Price = new JLabel();
    JLabel productTrainSets4Code = new JLabel();
    JLabel productTrainSets4Brand = new JLabel();
    JLabel productTrainSets4Scale = new JLabel();
    JButton productTrainSets4Info = new JButton();
    JButton productTrainSets4Cart = new JButton();
    JLabel productTrainSetsBorder = new JLabel();
    JLabel productBackground = new JLabel();

    JLabel padding = new JLabel("");
    JScrollPane scrollPane = new JScrollPane();
    JTextArea textArea = new JTextArea(100, 100);

    public void initFrame()
    {
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((Math.round(xSize)),9000);

        initPanel();

        productPageScrollPane.setViewportView(productPagePanel);
        productPageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        productPageScrollPane.setSize((Math.round(xSize)), (Math.round(ySize)));
        //JScrollBar verticalScrollBar = productPageScrollPane.createVerticalScrollBar();
        //verticalScrollBar.setSize((int) (Math.round(xSize * 0.001)),2000);
        this.add(productPageScrollPane, "align right");
        //this.add(verticalScrollBar);
        this.setVisible(true);
    }

    public void initPanel()
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

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Row indentation
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 12) );
        textArea.setEditable(false);
        textArea.setLineWrap(false);
        textArea.append("test");
        scrollPane.setRowHeaderView(padding);
        scrollPane.setViewportView(textArea);
        scrollPane.getRowHeader().setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);

        try {
            BufferedImage leftArrowImg = ImageIO.read(new File("leftArrowInactive.png"));
            Image leftArrowImgResized = leftArrowImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            leftArrow.setIcon(new ImageIcon(leftArrowImgResized));
        } catch (Exception e) {
            System.out.println("The file was not found.");
            e.printStackTrace();
        }
        leftArrow.setLocation((int) (Math.round(xSize * 0.18)),413);
        leftArrow.setSize(30,30);
        productPagePanel.add(leftArrow);

        try {
            BufferedImage rightArrowImg = ImageIO.read(new File("rightArrowActive.png"));
            Image rightArrowImgResized = rightArrowImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            rightArrow.setIcon(new ImageIcon(rightArrowImgResized));
        } catch (Exception e) {
            System.out.println("The file was not found.");
            e.printStackTrace();
        }
        rightArrow.setLocation((int) (Math.round(xSize * 0.925)),413);
        rightArrow.setSize(30,30);
        productPagePanel.add(rightArrow);

        productHeader.setLocation(0,0);
        productHeader.setSize((Math.round(xSize)),70);
        productHeader.setForeground( new Color(-1) );
        productHeader.setFont(new Font("Merryweather", Font.BOLD, 50));
        productHeader.setOpaque(true);
        productHeader.setBackground( new Color(-11854529) );
        productHeader.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productHeader.setText("Trains of Sheffield");
        productHeader.setHorizontalAlignment(SwingConstants.CENTER);
        productPagePanel.add(productHeader);

        productAccountButton.setLocation(0,70);
        productAccountButton.setSize((int) (Math.round(xSize * 0.16)),87);
        productAccountButton.setForeground( new Color(-1) );
        productAccountButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        productAccountButton.addActionListener(e->productAccountButton_Click());
        productAccountButton.setBackground( new Color(-2743738) );
        productAccountButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productAccountButton.setText("   Account");
        productAccountButton.setHorizontalAlignment(SwingConstants.LEFT);
        try {
            BufferedImage profilePictureImg = ImageIO.read(new File("profilePicture.png"));
            Image profilePictureImgResized = profilePictureImg.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
            profilePicture.setIcon(new ImageIcon(profilePictureImgResized));
        } catch (Exception e) {
            System.out.println("The file was not found.");
            e.printStackTrace();
        }
        //profilePicture.setLocation((int) (Math.round(xSize * 0.12)),70);
        profilePicture.setSize(70,70);
        productAccountButton.add(profilePicture);
        productPagePanel.add(productAccountButton);

        productBasketButton.setLocation(0,157);
        productBasketButton.setSize((int) (Math.round(xSize * 0.16)),87);
        productBasketButton.setForeground( new Color(-1) );
        productBasketButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        productBasketButton.addActionListener(e->productBasketButton_Click());
        productBasketButton.setBackground( new Color(-2743738) );
        productBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productBasketButton.setText("   Basket");
        productBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
        productPagePanel.add(productBasketButton);

        productStaffPageButton.setLocation(0,244);
        productStaffPageButton.setSize((int) (Math.round(xSize * 0.16)),87);
        productStaffPageButton.setForeground( new Color(-1) );
        productStaffPageButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        productStaffPageButton.addActionListener(e->productStaffPageButton_Click());
        productStaffPageButton.setBackground( new Color(-15440650) );
        productStaffPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productStaffPageButton.setText("   To Staff Page");
        productStaffPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        productPagePanel.add(productStaffPageButton);

        productSidebar.setLocation(0,70);
        productSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
        productSidebar.setOpaque(true);
        productSidebar.setBackground( new Color(-11854529) );
        productPagePanel.add(productSidebar);

        productTypeFilterLabel.setLocation((int) (Math.round(xSize * 0.16)),70);
        productTypeFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
        productTypeFilterLabel.setForeground( new Color(-1) );
        productTypeFilterLabel.setOpaque(true);
        productTypeFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productTypeFilterLabel.setBackground( new Color(0xFFD62246) );
        productTypeFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productTypeFilterLabel.setText("Filter by Product Type");
        productPagePanel.add(productTypeFilterLabel);

        productTypeFilterCombo.setLocation((int) (Math.round(xSize * 0.16)),114);
        productTypeFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
        productTypeFilterCombo.setForeground( new Color(-1) );
        productTypeFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productTypeFilterCombo.setBackground( new Color(-2743738) );
        productTypeFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productTypeFilterCombo);

        productBrandFilterLabel.setLocation((int) (Math.round(xSize * 0.3)),70);
        productBrandFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
        productBrandFilterLabel.setForeground( new Color(-1) );
        productBrandFilterLabel.setOpaque(true);
        productBrandFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productBrandFilterLabel.setBackground( new Color(0xFFD62246) );
        productBrandFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productBrandFilterLabel.setText("Filter by Brand");
        productPagePanel.add(productBrandFilterLabel);

        productBrandFilterCombo.setLocation((int) (Math.round(xSize * 0.3)),114);
        productBrandFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
        productBrandFilterCombo.setForeground( new Color(-1) );
        productBrandFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productBrandFilterCombo.setBackground( new Color(-2743738) );
        productBrandFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productBrandFilterCombo);

        productPriceFilterLabel.setLocation((int) (Math.round(xSize * 0.44)),70);
        productPriceFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
        productPriceFilterLabel.setForeground( new Color(-1) );
        productPriceFilterLabel.setOpaque(true);
        productPriceFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productPriceFilterLabel.setBackground( new Color(0xFFD62246) );
        productPriceFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPriceFilterLabel.setText("Filter by Price Range");
        productPagePanel.add(productPriceFilterLabel);

        productPriceFilterCombo.setLocation((int) (Math.round(xSize * 0.44)),114);
        productPriceFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
        productPriceFilterCombo.setForeground( new Color(-1) );
        productPriceFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productPriceFilterCombo.setBackground( new Color(-2743738) );
        productPriceFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productPriceFilterCombo);

        productScaleFilterLabel.setLocation((int) (Math.round(xSize * 0.58)),70);
        productScaleFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
        productScaleFilterLabel.setForeground( new Color(-1) );
        productScaleFilterLabel.setOpaque(true);
        productScaleFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productScaleFilterLabel.setBackground( new Color(0xFFD62246) );
        productScaleFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productScaleFilterLabel.setText("Filter by Modelling Scale");
        productPagePanel.add(productScaleFilterLabel);

        productScaleFilterCombo.setLocation((int) (Math.round(xSize * 0.58)),114);
        productScaleFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
        productScaleFilterCombo.setForeground( new Color(-1) );
        productScaleFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productScaleFilterCombo.setBackground( new Color(-2743738) );
        productScaleFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productScaleFilterCombo);

        productSearch.setLocation((int) (Math.round(xSize * 0.75)),100);
        productSearch.setSize((int) (Math.round(xSize * 0.2)),26);
        productSearch.setFont(new Font("Merriweather", Font.BOLD, 14));
        productSearch.setBackground( new Color(0xFFFFFF) );
        productSearch.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productSearch.setText("Search");
        productPagePanel.add(productSearch);

        productFilterBar.setLocation((int) (Math.round(xSize * 0.16)),70);
        productFilterBar.setSize((int) (Math.round(xSize * 0.84)),87);
        productFilterBar.setForeground( new Color(-1) );
        productFilterBar.setOpaque(true);
        productFilterBar.setBackground( new Color(-14995422) );
        productFilterBar.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productFilterBar);

        productTrainSetsText.setLocation((int) (Math.round(xSize * 0.20)),179);
        productTrainSetsText.setSize((int) (Math.round(xSize * 0.22)),44);
        productTrainSetsText.setForeground( new Color(-1) );
        productTrainSetsText.setFont(new Font("Merriweather", Font.BOLD, 35));
        productTrainSetsText.setBackground( new Color(-14995422) );
        productTrainSetsText.setText("Train Sets");
        productPagePanel.add(productTrainSetsText);

        productTrainSetsPageCount.setLocation((int) (Math.round(xSize * 0.87)),179);
        productTrainSetsPageCount.setSize((int) (Math.round(xSize * 0.06)),40);
        productTrainSetsPageCount.setForeground( new Color(-1) );
        productTrainSetsPageCount.setFont(new Font("Merriweather", Font.BOLD, 35));
        productTrainSetsPageCount.setBackground( new Color(-14995422) );
        productTrainSetsPageCount.setText("1/1");
        productPagePanel.add(productTrainSetsPageCount);

        productTrainSets1Name.setLocation((int) (Math.round(xSize * 0.205)),220);
        productTrainSets1Name.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets1Name.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets1Name.setText("Eurostar Train Set");
        productPagePanel.add(productTrainSets1Name);

        productTrainSets1Price.setLocation((int) (Math.round(xSize * 0.205)),240);
        productTrainSets1Price.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets1Price.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets1Price.setText("£250.00");
        productPagePanel.add(productTrainSets1Price);

        productTrainSets1Code.setLocation((int) (Math.round(xSize * 0.205)),260);
        productTrainSets1Code.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets1Code.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets1Code.setText("Product Code: M58392");
        productPagePanel.add(productTrainSets1Code);

        productTrainSets1Brand.setLocation((int) (Math.round(xSize * 0.205)),280);
        productTrainSets1Brand.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets1Brand.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets1Brand.setText("Brand: Peco");
        productPagePanel.add(productTrainSets1Brand);

        productTrainSets1Scale.setLocation((int) (Math.round(xSize * 0.205)),300);
        productTrainSets1Scale.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets1Scale.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets1Scale.setText("Modelling Scale: N Gauge");
        productPagePanel.add(productTrainSets1Scale);

        productTrainSets1Info.setLocation((int) (Math.round(xSize * 0.225)),340);
        productTrainSets1Info.setSize((int) (Math.round(xSize * 0.14)),70);
        productTrainSets1Info.setForeground( new Color(-1) );
        productTrainSets1Info.setFont(new Font("Merriweather", Font.BOLD, 17));
        productTrainSets1Info.addActionListener(e->productTrainSets1Info_Click());
        productTrainSets1Info.setBackground( new Color(-2743738) );
        productTrainSets1Info.setText("More Information");
        productPagePanel.add(productTrainSets1Info);

        productTrainSets1Cart.setLocation((int) (Math.round(xSize * 0.375)),340);
        productTrainSets1Cart.setSize((int) (Math.round(xSize * 0.06)),70);
        productTrainSets1Cart.setForeground( new Color(-1) );
        productTrainSets1Cart.setFont(new Font("Merriweather", Font.BOLD, 40));
        productTrainSets1Cart.addActionListener(e->productTrainSets1Info_Click());
        productTrainSets1Cart.setBackground( new Color(-2743738) );
        productTrainSets1Cart.setText("+");
        productPagePanel.add(productTrainSets1Cart);

        productTrainSets1.setLocation((int) (Math.round(xSize * 0.20)),230);
        productTrainSets1.setSize((int) (Math.round(xSize * 0.24)),195);
        productTrainSets1.setOpaque(true);
        productTrainSets1.setBackground( new Color(-1) );
        productPagePanel.add(productTrainSets1);

        productTrainSets2Name.setLocation((int) (Math.round(xSize * 0.45)),220);
        productTrainSets2Name.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets2Name.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets2Name.setText("Mallard Record Breaker Train Set");
        productPagePanel.add(productTrainSets2Name);

        productTrainSets2Price.setLocation((int) (Math.round(xSize * 0.45)),240);
        productTrainSets2Price.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets2Price.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets2Price.setText("£340.00");
        productPagePanel.add(productTrainSets2Price);

        productTrainSets2Code.setLocation((int) (Math.round(xSize * 0.45)),260);
        productTrainSets2Code.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets2Code.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets2Code.setText("Product Code: M398");
        productPagePanel.add(productTrainSets2Code);

        productTrainSets2Brand.setLocation((int) (Math.round(xSize * 0.45)),280);
        productTrainSets2Brand.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets2Brand.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets2Brand.setText("Brand: Bachmann");
        productPagePanel.add(productTrainSets2Brand);

        productTrainSets2Scale.setLocation((int) (Math.round(xSize * 0.45)),300);
        productTrainSets2Scale.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets2Scale.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets2Scale.setText("Modelling Scale: TT Gauge");
        productPagePanel.add(productTrainSets2Scale);

        productTrainSets2Info.setLocation((int) (Math.round(xSize * 0.47)),340);
        productTrainSets2Info.setSize((int) (Math.round(xSize * 0.14)),70);
        productTrainSets2Info.setForeground( new Color(-1) );
        productTrainSets2Info.setFont(new Font("Merriweather", Font.BOLD, 17));
        productTrainSets2Info.addActionListener(e->productTrainSets2Info_Click());
        productTrainSets2Info.setBackground( new Color(-2743738) );
        productTrainSets2Info.setText("More Information");
        productPagePanel.add(productTrainSets2Info);

        productTrainSets2Cart.setLocation((int) (Math.round(xSize * 0.62)),340);
        productTrainSets2Cart.setSize((int) (Math.round(xSize * 0.06)),70);
        productTrainSets2Cart.setForeground( new Color(-1) );
        productTrainSets2Cart.setFont(new Font("Merriweather", Font.BOLD, 40));
        productTrainSets2Cart.addActionListener(e->productTrainSets2Info_Click());
        productTrainSets2Cart.setBackground( new Color(-2743738) );
        productTrainSets2Cart.setText("+");
        productPagePanel.add(productTrainSets2Cart);

        productTrainSets2.setLocation((int) (Math.round(xSize * 0.445)),230);
        productTrainSets2.setSize((int) (Math.round(xSize * 0.24)),195);
        productTrainSets2.setOpaque(true);
        productTrainSets2.setBackground( new Color(-1) );
        productPagePanel.add(productTrainSets2);

        productTrainSets3Name.setLocation((int) (Math.round(xSize * 0.695)),220);
        productTrainSets3Name.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets3Name.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets3Name.setText("Flying Scotsman Train Set");
        productPagePanel.add(productTrainSets3Name);

        productTrainSets3Price.setLocation((int) (Math.round(xSize * 0.695)),240);
        productTrainSets3Price.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets3Price.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets3Price.setText("£220.00");
        productPagePanel.add(productTrainSets3Price);

        productTrainSets3Code.setLocation((int) (Math.round(xSize * 0.695)),260);
        productTrainSets3Code.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets3Code.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets3Code.setText("Product Code: M4032");
        productPagePanel.add(productTrainSets3Code);

        productTrainSets3Brand.setLocation((int) (Math.round(xSize * 0.695)),280);
        productTrainSets3Brand.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets3Brand.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets3Brand.setText("Brand: Peco");
        productPagePanel.add(productTrainSets3Brand);

        productTrainSets3Scale.setLocation((int) (Math.round(xSize * 0.695)),300);
        productTrainSets3Scale.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets3Scale.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets3Scale.setText("Modelling Scale: N Gauge");
        productPagePanel.add(productTrainSets3Scale);

        productTrainSets3Info.setLocation((int) (Math.round(xSize * 0.715)),340);
        productTrainSets3Info.setSize((int) (Math.round(xSize * 0.14)),70);
        productTrainSets3Info.setForeground( new Color(-1) );
        productTrainSets3Info.setFont(new Font("Merriweather", Font.BOLD, 17));
        productTrainSets3Info.addActionListener(e->productTrainSets3Info_Click());
        productTrainSets3Info.setBackground( new Color(-2743738) );
        productTrainSets3Info.setText("More Information");
        productPagePanel.add(productTrainSets3Info);

        productTrainSets3Cart.setLocation((int) (Math.round(xSize * 0.865)),340);
        productTrainSets3Cart.setSize((int) (Math.round(xSize * 0.06)),70);
        productTrainSets3Cart.setForeground( new Color(-1) );
        productTrainSets3Cart.setFont(new Font("Merriweather", Font.BOLD, 40));
        productTrainSets3Cart.addActionListener(e->productTrainSets3Info_Click());
        productTrainSets3Cart.setBackground( new Color(-2743738) );
        productTrainSets3Cart.setText("+");
        productPagePanel.add(productTrainSets3Cart);

        productTrainSets3.setLocation((int) (Math.round(xSize * 0.69)),230);
        productTrainSets3.setSize((int) (Math.round(xSize * 0.24)),195);
        productTrainSets3.setOpaque(true);
        productTrainSets3.setBackground( new Color(-1) );
        productPagePanel.add(productTrainSets3);

        productTrainSets4Name.setLocation((int) (Math.round(xSize * 0.205)),425);
        productTrainSets4Name.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets4Name.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets4Name.setText("Mixed Freight DCC Train Set");
        productPagePanel.add(productTrainSets4Name);

        productTrainSets4Price.setLocation((int) (Math.round(xSize * 0.205)),445);
        productTrainSets4Price.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets4Price.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets4Price.setText("£200.00");
        productPagePanel.add(productTrainSets4Price);

        productTrainSets4Code.setLocation((int) (Math.round(xSize * 0.205)),465);
        productTrainSets4Code.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets4Code.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets4Code.setText("Product Code: M89410");
        productPagePanel.add(productTrainSets4Code);

        productTrainSets4Brand.setLocation((int) (Math.round(xSize * 0.205)),485);
        productTrainSets4Brand.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets4Brand.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets4Brand.setText("Brand: Hornby");
        productPagePanel.add(productTrainSets4Brand);

        productTrainSets4Scale.setLocation((int) (Math.round(xSize * 0.205)),505);
        productTrainSets4Scale.setSize((int) (Math.round(xSize * 0.22)),40);
        productTrainSets4Scale.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSets4Scale.setText("Modelling Scale: OO Gauge");
        productPagePanel.add(productTrainSets4Scale);

        productTrainSets4Info.setLocation((int) (Math.round(xSize * 0.225)),545);
        productTrainSets4Info.setSize((int) (Math.round(xSize * 0.14)),70);
        productTrainSets4Info.setForeground( new Color(-1) );
        productTrainSets4Info.setFont(new Font("Merriweather", Font.BOLD, 17));
        productTrainSets4Info.addActionListener(e->productTrainSets4Info_Click());
        productTrainSets4Info.setBackground( new Color(-2743738) );
        productTrainSets4Info.setText("More Information");
        productPagePanel.add(productTrainSets4Info);

        productTrainSets4Cart.setLocation((int) (Math.round(xSize * 0.375)),545);
        productTrainSets4Cart.setSize((int) (Math.round(xSize * 0.06)),70);
        productTrainSets4Cart.setForeground( new Color(-1) );
        productTrainSets4Cart.setFont(new Font("Merriweather", Font.BOLD, 40));
        productTrainSets4Cart.addActionListener(e->productTrainSets4Info_Click());
        productTrainSets4Cart.setBackground( new Color(-2743738) );
        productTrainSets4Cart.setText("+");
        productPagePanel.add(productTrainSets4Cart);

        productTrainSets4.setLocation((int) (Math.round(xSize * 0.2)),435);
        productTrainSets4.setSize((int) (Math.round(xSize * 0.24)),195);
        productTrainSets4.setOpaque(true);
        productTrainSets4.setBackground( new Color(-1) );
        productPagePanel.add(productTrainSets4);

        productTrainSetsBorder.setLocation((int) (Math.round(xSize * 0.19)),175);
        productTrainSetsBorder.setSize((int) (Math.round(xSize * 0.75)),464);
        productTrainSetsBorder.setForeground( new Color(-1) );
        productTrainSetsBorder.setOpaque(true);
        productTrainSetsBorder.setBackground( new Color(-14995422) );
        productPagePanel.add(productTrainSetsBorder);

        productBackground.setLocation(0,0);
        productBackground.setSize((Math.round(xSize)),9000);
        productBackground.setOpaque(true);
        productBackground.setBackground( new Color(-8741250) );
        productPagePanel.add(productBackground);

        productPagePanel.setVisible(true);
    }

    public void productAccountButton_Click()
    {
        System.out.println("productAccountButton_Click() has been pressed ");
    }
    public void productBasketButton_Click()
    {
        System.out.println("productBasketButton_Click() has been pressed ");
    }
    public void productStaffPageButton_Click()
    {
        System.out.println("productStaffPageButton_Click() has been pressed ");
    }
    public void productTrainSets1Info_Click()
    {
        System.out.println("Placeholder");
    }
    public void productTrainSets1Cart_Click()
    {
        System.out.println("Placeholder");
    }
    public void productTrainSets2Info_Click()
    {
        System.out.println("Placeholder");
    }
    public void productTrainSets2Cart_Click()
    {
        System.out.println("Placeholder");
    }
    public void productTrainSets3Info_Click()
    {
        System.out.println("Placeholder");
    }
    public void productTrainSets3Cart_Click()
    {
        System.out.println("Placeholder");
    }
    public void productTrainSets4Info_Click()
    {
        System.out.println("Placeholder");
    }
    public void productTrainSets4Cart_Click()
    {
        System.out.println("Placeholder");
    }

}
