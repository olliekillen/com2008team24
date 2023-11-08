import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Stack;

//break class down into smaller parts (it should not be 600 lines).

public class AccountPage extends JFrame {

	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	JPanel productPagePanel = new JPanel(null);
	JScrollPane productPageScrollPane = new JScrollPane(null);

	JLabel profilePicture = new JLabel();
	JLabel pageTitle = new JLabel();
	JButton accountButton = new JButton();
	JButton productBasketButton = new JButton();
	JButton staffButton = new JButton();
	JLabel productSidebar = new JLabel();
	JLabel accountDetailTitleText = new JLabel();
	JLabel accountDetailArea = new JLabel();
	JLabel accountNameText = new JLabel();
	JLabel accountPasswordText = new JLabel();
	JLabel accountEmailText = new JLabel();
	JLabel accountDetailAreaBrand = new JLabel();
	JLabel accountBankDetails = new JLabel();
	JButton accountDetailAreaInfo = new JButton();
	JButton accountEditButton = new JButton();
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
	JLabel accountDetailBackGround = new JLabel();
	JLabel acountPageBackground = new JLabel();

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





		pageTitle.setLocation(0,0);
		pageTitle.setSize((Math.round(xSize)),70);
		pageTitle.setForeground( new Color(-1) );
		pageTitle.setFont(new Font("Merryweather", Font.BOLD, 50));
		pageTitle.setOpaque(true);
		pageTitle.setBackground( new Color(-11854529) );
		pageTitle.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		pageTitle.setText("Trains of Sheffield");
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		productPagePanel.add(pageTitle);

		accountButton.setLocation(0,70);
		accountButton.setSize((int) (Math.round(xSize * 0.16)),87);
		accountButton.setForeground( new Color(-1) );
		accountButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		accountButton.addActionListener(e->accountButton_Click());
		accountButton.setBackground( new Color(-2743738) );
		accountButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		accountButton.setText("   Account");
		accountButton.setHorizontalAlignment(SwingConstants.LEFT);
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
		accountButton.add(profilePicture);
		productPagePanel.add(accountButton);

		productBasketButton.setLocation(0,157);
		productBasketButton.setSize((int) (Math.round(xSize * 0.16)),87);
		productBasketButton.setForeground( new Color(-1) );
		productBasketButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		productBasketButton.addActionListener(e->basketButton_Click());
		productBasketButton.setBackground( new Color(-2743738) );
		productBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		productBasketButton.setText("   Basket");
		productBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
		productPagePanel.add(productBasketButton);

		staffButton.setLocation(0,244);
		staffButton.setSize((int) (Math.round(xSize * 0.16)),87);
		staffButton.setForeground( new Color(-1) );
		staffButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		staffButton.addActionListener(e->staffButton_Click());
		staffButton.setBackground( new Color(-15440650) );
		staffButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		staffButton.setText("   To Staff Page");
		staffButton.setHorizontalAlignment(SwingConstants.LEFT);
		productPagePanel.add(staffButton);

		productSidebar.setLocation(0,70);
		productSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
		productSidebar.setOpaque(true);
		productSidebar.setBackground( new Color(-11854529) );
		productPagePanel.add(productSidebar);


		accountDetailTitleText.setLocation((int) (Math.round(xSize * 0.20)),179);
		accountDetailTitleText.setSize((int) (Math.round(xSize * 0.22)),44);
		accountDetailTitleText.setForeground( new Color(-1) );
		accountDetailTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
		accountDetailTitleText.setBackground( new Color(-14995422) );
		accountDetailTitleText.setText("Acount Details ");
		productPagePanel.add(accountDetailTitleText);

		accountNameText.setLocation((int) (Math.round(xSize * 0.205)),230);
		accountNameText.setSize((int) (Math.round(xSize * 0.22)),40);
		accountNameText.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountNameText.setText("Name:");
		productPagePanel.add(accountNameText);

		accountPasswordText.setLocation((int) (Math.round(xSize * 0.205)),265);
		accountPasswordText.setSize((int) (Math.round(xSize * 0.22)),40);
		accountPasswordText.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountPasswordText.setText("Password:");
		productPagePanel.add(accountPasswordText);

		accountEmailText.setLocation((int) (Math.round(xSize * 0.205)),300);
		accountEmailText.setSize((int) (Math.round(xSize * 0.22)),40);
		accountEmailText.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountEmailText.setText("Email:");
		productPagePanel.add(accountEmailText);

		accountDetailAreaBrand.setLocation((int) (Math.round(xSize * 0.205)),340);
		accountDetailAreaBrand.setSize((int) (Math.round(xSize * 0.22)),40);
		accountDetailAreaBrand.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountDetailAreaBrand.setText("Address:");
		productPagePanel.add(accountDetailAreaBrand);

		accountBankDetails.setLocation((int) (Math.round(xSize * 0.205)),380);
		accountBankDetails.setSize((int) (Math.round(xSize * 0.22)),40);
		accountBankDetails.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountBankDetails.setText("Bank Details:");
		productPagePanel.add(accountBankDetails);


		accountEditButton.setLocation((int) (Math.round(xSize * 0.375)),400);
		accountEditButton.setSize((int) (Math.round(xSize * 0.06)),70);
		accountEditButton.setForeground( new Color(-1) );
		accountEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
		accountEditButton.addActionListener(e->editButton_Click());
		accountEditButton.setOpaque(false);
		accountEditButton.setContentAreaFilled(false);
		accountEditButton.setFocusPainted(false);
		accountEditButton.setOpaque(false);
		accountEditButton.setText("edit");
		productPagePanel.add(accountEditButton);

		accountDetailArea.setLocation((int) (Math.round(xSize * 0.20)),230);
		accountDetailArea.setSize((int) (Math.round(xSize * 0.72)),750); // Account Detail inner background
		accountDetailArea.setOpaque(true);
		accountDetailArea.setBackground( new Color(-1) );
		productPagePanel.add(accountDetailArea);


		accountDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),175);
		accountDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),850); // Green background
		accountDetailBackGround.setForeground( new Color(-1) );
		accountDetailBackGround.setOpaque(true);
		accountDetailBackGround.setBackground( new Color(-14995422) );
		productPagePanel.add(accountDetailBackGround);

		acountPageBackground.setLocation(0,0);
		acountPageBackground.setSize((Math.round(xSize)),9000);
		acountPageBackground.setOpaque(true);
		acountPageBackground.setBackground( new Color(-8741250) );
		productPagePanel.add(acountPageBackground);

		productPagePanel.setVisible(true);
	}

	public void accountButton_Click()
	{
		System.out.println("accountButton_Click() has been pressed ");
	}
	public void basketButton_Click()
	{
		System.out.println("basketButton_Click() has been pressed ");
	}
	public void staffButton_Click()
	{
		System.out.println("staffButton_Click() has been pressed ");
	}
	public void editButton_Click()
	{
		System.out.println("Placeholder");
	}


	public static void main(String args[]) {
		final AccountPage window = new AccountPage();
		window.initPanel();
		window.initFrame();
	}


}