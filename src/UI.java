import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class UI {

	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private GraphicsDevice gd = ge.getDefaultScreenDevice();
	private int screenWidth = gd.getDisplayMode().getWidth();
	private int screenHeight = gd.getDisplayMode().getHeight();

	protected static JFrame generalFrame = new JFrame();

	private static JButton goToLoginPageButton = new JButton("Login");
	private static JButton goToSignUpPageButton = new JButton("Sign Up");
	private static JButton goToHomePageButton = new JButton("Home");
	protected static JPanel goToLoginPageButtonPanel = new JPanel();
	protected static JPanel goToSignUpPageButtonPanel = new JPanel();
	protected static JPanel goToHomePageButtonPanel = new JPanel();

	protected abstract void formatNavBar(JPanel navBar);

	protected abstract void generatePage();

	/**
	 * Formats the size and location of the frame.
	 */
	protected void setFrameProperties() {
		generalFrame.setMinimumSize(new Dimension(screenWidth / 3, (int) (screenHeight / 1.5)));
		generalFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		generalFrame.setLocationRelativeTo(null);
		generalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		generalFrame.setVisible(true);
	}

	/**
	 * Sets the layout of the navBar
	 */
	protected void setNavBarLayout(JPanel navBar) {
		navBar.setLayout(new BoxLayout(navBar, BoxLayout.X_AXIS));
		navBar.setBorder(new EmptyBorder(20, 20, 20, 20));
	}

	/**
	 * Sets the size of the JPanels containing the components and adds the
	 * components to their respective JPanels. The components are added to JPanels
	 * to help with sizing and positioning when using them in layouts.
	 */
	protected void addComponentsToPanels() {
		goToSignUpPageButtonPanel.setMinimumSize(new Dimension(0, 50));
		goToSignUpPageButtonPanel.setMaximumSize(new Dimension(0, 50));
		goToLoginPageButtonPanel.setMinimumSize(new Dimension(0, 50));
		goToLoginPageButtonPanel.setMaximumSize(new Dimension(0, 50));
		goToHomePageButtonPanel.setMinimumSize(new Dimension(0, 50));
		goToHomePageButtonPanel.setMaximumSize(new Dimension(0, 50));

		goToSignUpPageButtonPanel.add(createCustomJButton(goToSignUpPageButton));
		goToLoginPageButtonPanel.add(createCustomJButton(goToLoginPageButton));
		goToHomePageButtonPanel.add(createCustomJButton(goToHomePageButton));
	}

	protected void addListenersToPageChangingButtons() {
		goToSignUpPageButton.addActionListener(e -> {
			SignUpUI signUpPage = new SignUpUI();
			generalFrame.getContentPane().removeAll();
			signUpPage.generatePage();
			generalFrame.setContentPane(signUpPage.getSignUpPagePanel());
			generalFrame.revalidate();
		});

		goToLoginPageButton.addActionListener(e -> {
			LoginUI loginPage = new LoginUI();
			generalFrame.getContentPane().removeAll();
			loginPage.generatePage();
			generalFrame.setContentPane(loginPage.getLoginPagePanel());
			generalFrame.revalidate();
		});

		goToHomePageButton.addActionListener(e -> {
			HomePageUI homePage = new HomePageUI();
			generalFrame.getContentPane().removeAll();
			homePage.generatePage();
			generalFrame.setContentPane(homePage.getHomePagePanel());
			generalFrame.revalidate();
		});
	}

	/**
	 * Customises JButton appearance.
	 * 
	 * @param button
	 */
	protected JButton createCustomJButton(JButton button) {
		button.setPreferredSize(new Dimension(100, 40));
		button.setBorder(new LineBorder(Color.BLACK));
		return button;
	}

	/**
	 * Customises JLabel appearance for forms.
	 * 
	 * @param label
	 */
	protected void createCustomFieldJLabel(JLabel label) {
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setBorder(new EmptyBorder(10, 0, 0, 0));
		label.setFont(new Font("Arial", Font.PLAIN, 15));
	}

	/**
	 * Customises JLabel appearance for forms titles e.g. "Login" and "Sign Up" at
	 * the top of the forms.
	 * 
	 * @param label
	 */
	protected void createCustomFormTitleLabel(JLabel label) {
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setBorder(new EmptyBorder(0, 0, 20, 0));
		label.setFont(new Font("Arial", Font.PLAIN, 30));
	}

	/**
	 * Customises JTextField appearance.
	 * 
	 * @param field
	 * @param panel
	 */
	protected void createCustomFields(JTextField field, JPanel panel) {
		field.setPreferredSize(new Dimension(200, 30));
		field.setHorizontalAlignment(SwingConstants.CENTER);

		panel.setMinimumSize(new Dimension(500, 40));
		panel.setMaximumSize(new Dimension(500, 40));

		panel.add(field);
	}
}
