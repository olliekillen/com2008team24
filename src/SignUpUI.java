import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SignUpUI extends UI {

	// If certain components are only used in one method move them into it! In all
	// classes.

	// Consider running methods that customise static methods in main. (New made
	// methods)

	private JPanel signUpPagePanel = new JPanel(new BorderLayout());

	@Override
	protected void formatNavBar(JPanel navBar) {
		setNavBarLayout(navBar);
		navBar.setBackground(Color.blue);
		navBar.add(Box.createHorizontalGlue());
		navBar.add(goToLoginPageButtonPanel);
		navBar.add(goToHomePageButtonPanel);

	}

	@Override
	protected void generatePage() {
		JPanel signUpPageNavBar = new JPanel();
		formatNavBar(signUpPageNavBar);
		signUpPagePanel.add(signUpPageNavBar, BorderLayout.NORTH);

		JPanel signUpFormPanel = new JPanel();
		generateSignUpPageForm(signUpFormPanel);
		signUpPagePanel.add(signUpFormPanel, BorderLayout.CENTER);

	}

// Reconsider layout
	private void generateSignUpPageForm(JPanel signUpFormPanel) {
		JPanel signUpButtonPanel = new JPanel();

		signUpFormPanel.setLayout(new BoxLayout(signUpFormPanel, BoxLayout.Y_AXIS));
		signUpButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		signUpFormPanel.add(Box.createVerticalGlue());

		JLabel signUpLabel = new JLabel("Sign Up");
		createCustomFormTitleLabel(signUpLabel);
		signUpFormPanel.add(signUpLabel);

		// Maybe make this a general function as well
		JLabel forenameFieldLabel = new JLabel("Forename");
		JTextField forenameField = new JTextField();
		JPanel forenameFieldPanel = new JPanel();
		createCustomFieldJLabel(forenameFieldLabel);
		signUpFormPanel.add(forenameFieldLabel);
		createCustomFields(forenameField, forenameFieldPanel);
		signUpFormPanel.add(forenameFieldPanel);

		JLabel surnameFieldLabel = new JLabel("Surname");
		JTextField surnameField = new JTextField();
		JPanel surnameFieldPanel = new JPanel();
		createCustomFieldJLabel(surnameFieldLabel);
		signUpFormPanel.add(surnameFieldLabel);
		createCustomFields(surnameField, surnameFieldPanel);
		signUpFormPanel.add(surnameFieldPanel);

		JLabel signUpEmailFieldLabel = new JLabel("Email");
		JTextField signUpEmailField = new JTextField();
		JPanel signUpEmailFieldPanel = new JPanel();
		createCustomFieldJLabel(signUpEmailFieldLabel);
		signUpFormPanel.add(signUpEmailFieldLabel);
		createCustomFields(signUpEmailField, signUpEmailFieldPanel);
		signUpFormPanel.add(signUpEmailFieldPanel);

		JLabel signUpPasswordFieldLabel = new JLabel("Password");
		JTextField signUpPasswordField = new JTextField();
		JPanel signUpPasswordFieldPanel = new JPanel();
		createCustomFieldJLabel(signUpPasswordFieldLabel);
		signUpFormPanel.add(signUpPasswordFieldLabel);
		createCustomFields(signUpPasswordField, signUpPasswordFieldPanel);
		signUpFormPanel.add(signUpPasswordFieldPanel);

		JPasswordField confirmPasswordField = new JPasswordField();
		JLabel confirmPasswordFieldLabel = new JLabel("Confirm Password");
		JPanel confirmPasswordFieldPanel = new JPanel();
		createCustomFieldJLabel(confirmPasswordFieldLabel);
		signUpFormPanel.add(confirmPasswordFieldLabel);
		createCustomFields(confirmPasswordField, confirmPasswordFieldPanel);
		signUpFormPanel.add(confirmPasswordFieldPanel);

		JLabel postcodeFieldLabel = new JLabel("Postcode");
		JTextField postcodeField = new JTextField();
		JPanel postodeFieldPanel = new JPanel();
		createCustomFieldJLabel(postcodeFieldLabel);
		signUpFormPanel.add(postcodeFieldLabel);
		createCustomFields(postcodeField, postodeFieldPanel);
		signUpFormPanel.add(postodeFieldPanel);

		JLabel houseNumberFieldLabel = new JLabel("House Number");
		JTextField houseNumberField = new JTextField();
		JPanel houseNumberFieldPanel = new JPanel();
		createCustomFieldJLabel(houseNumberFieldLabel);
		signUpFormPanel.add(houseNumberFieldLabel);
		createCustomFields(houseNumberField, houseNumberFieldPanel);
		signUpFormPanel.add(houseNumberFieldPanel);

		JLabel roadNameFieldLabel = new JLabel("Road Name");
		JTextField roadNameNumberField = new JTextField();
		JPanel roadNameNumberFieldPanel = new JPanel();
		createCustomFieldJLabel(roadNameFieldLabel);
		signUpFormPanel.add(roadNameFieldLabel);
		createCustomFields(roadNameNumberField, roadNameNumberFieldPanel);
		signUpFormPanel.add(roadNameNumberFieldPanel);

		JLabel cityNameFieldLabel = new JLabel("City Name");
		JTextField cityNameNumberField = new JTextField();
		JPanel cityNameNumberFieldPanel = new JPanel();
		createCustomFieldJLabel(cityNameFieldLabel);
		signUpFormPanel.add(cityNameFieldLabel);
		createCustomFields(cityNameNumberField, cityNameNumberFieldPanel);
		signUpFormPanel.add(cityNameNumberFieldPanel);

		JLabel signUpButtonPadding = new JLabel();
		signUpButtonPadding.setBorder(new EmptyBorder(30, 0, 0, 0));
		signUpFormPanel.add(signUpButtonPadding);

		JButton signUpButton = new JButton("Sign Up");

		createCustomJButton(signUpButton);
		signUpButtonPanel.add(signUpButton);
		signUpFormPanel.add(signUpButtonPanel);

		signUpFormPanel.add(Box.createVerticalGlue());

	}

	public JPanel getSignUpPagePanel() {
		return signUpPagePanel;
	}

}
