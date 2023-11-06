import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

		signUpFormPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		signUpButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		signUpFormPanel.add(Box.createVerticalGlue());

		constraints.insets = new Insets(0, 10, 0, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;

		JLabel signUpLabel = new JLabel("Sign Up");
		createCustomFormTitleLabel(signUpLabel);
		signUpFormPanel.add(signUpLabel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;

		// Maybe make this a general function as well
		JLabel forenameFieldLabel = new JLabel("Forename");
		JTextField forenameField = new JTextField();
		JPanel forenameFieldPanel = new JPanel();
		forenameFieldPanel.setLayout(new BoxLayout(forenameFieldPanel, BoxLayout.Y_AXIS));
		createCustomFieldJLabel(forenameFieldLabel);
		forenameFieldPanel.add(forenameFieldLabel);
		createCustomFields(forenameField, forenameFieldPanel);
		signUpFormPanel.add(forenameFieldPanel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;

		JLabel surnameFieldLabel = new JLabel("Surname");
		JTextField surnameField = new JTextField();
		JPanel surnameFieldPanel = new JPanel();
		surnameFieldPanel.setLayout(new BoxLayout(surnameFieldPanel, BoxLayout.Y_AXIS));
		createCustomFieldJLabel(surnameFieldLabel);
		surnameFieldPanel.add(surnameFieldLabel);
		createCustomFields(surnameField, surnameFieldPanel);
		signUpFormPanel.add(surnameFieldPanel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;

		JLabel signUpEmailFieldLabel = new JLabel("Email");
		JTextField signUpEmailField = new JTextField();
		JPanel signUpEmailFieldPanel = new JPanel();
		signUpEmailFieldPanel.setLayout(new BoxLayout(signUpEmailFieldPanel, BoxLayout.Y_AXIS));
		createCustomFieldJLabel(signUpEmailFieldLabel);
		signUpEmailFieldPanel.add(signUpEmailFieldLabel);
		createCustomFields(signUpEmailField, signUpEmailFieldPanel);
		signUpFormPanel.add(signUpEmailFieldPanel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;

		JLabel signUpPasswordFieldLabel = new JLabel("Password");
		JTextField signUpPasswordField = new JTextField();
		JPanel signUpPasswordFieldPanel = new JPanel();
		signUpPasswordFieldPanel.setLayout(new BoxLayout(signUpPasswordFieldPanel, BoxLayout.Y_AXIS));
		createCustomFieldJLabel(signUpPasswordFieldLabel);
		signUpPasswordFieldPanel.add(signUpPasswordFieldLabel);
		createCustomFields(signUpPasswordField, signUpPasswordFieldPanel);
		signUpFormPanel.add(signUpPasswordFieldPanel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;

		JPasswordField confirmPasswordField = new JPasswordField();
		JLabel confirmPasswordFieldLabel = new JLabel("Confirm Password");
		JPanel confirmPasswordFieldPanel = new JPanel();
		confirmPasswordFieldPanel.setLayout(new BoxLayout(confirmPasswordFieldPanel, BoxLayout.Y_AXIS));
		createCustomFieldJLabel(confirmPasswordFieldLabel);
		confirmPasswordFieldPanel.add(confirmPasswordFieldLabel);
		createCustomFields(confirmPasswordField, confirmPasswordFieldPanel);
		signUpFormPanel.add(confirmPasswordFieldPanel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;

		JLabel postcodeFieldLabel = new JLabel("Postcode");
		JTextField postcodeField = new JTextField();
		JPanel postodeFieldPanel = new JPanel();
		postodeFieldPanel.setLayout(new BoxLayout(postodeFieldPanel, BoxLayout.Y_AXIS));
		createCustomFieldJLabel(postcodeFieldLabel);
		postodeFieldPanel.add(postcodeFieldLabel);
		createCustomFields(postcodeField, postodeFieldPanel);
		signUpFormPanel.add(postodeFieldPanel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;

		JLabel houseNumberFieldLabel = new JLabel("House Number");
		JTextField houseNumberField = new JTextField();
		JPanel houseNumberFieldPanel = new JPanel();
		houseNumberFieldPanel.setLayout(new BoxLayout(houseNumberFieldPanel, BoxLayout.Y_AXIS));
		createCustomFieldJLabel(houseNumberFieldLabel);
		houseNumberFieldPanel.add(houseNumberFieldLabel);
		createCustomFields(houseNumberField, houseNumberFieldPanel);
		signUpFormPanel.add(houseNumberFieldPanel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;

		JLabel roadNameFieldLabel = new JLabel("Road Name");
		JTextField roadNameNumberField = new JTextField();
		JPanel roadNameNumberFieldPanel = new JPanel();
		roadNameNumberFieldPanel.setLayout(new BoxLayout(roadNameNumberFieldPanel, BoxLayout.Y_AXIS));
		createCustomFieldJLabel(roadNameFieldLabel);
		roadNameNumberFieldPanel.add(roadNameFieldLabel);
		createCustomFields(roadNameNumberField, roadNameNumberFieldPanel);
		signUpFormPanel.add(roadNameNumberFieldPanel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 1;

		JLabel cityNameFieldLabel = new JLabel("City Name");
		JTextField cityNameNumberField = new JTextField();
		JPanel cityNameNumberFieldPanel = new JPanel();
		cityNameNumberFieldPanel.setLayout(new BoxLayout(cityNameNumberFieldPanel, BoxLayout.Y_AXIS));
		createCustomFieldJLabel(cityNameFieldLabel);
		cityNameNumberFieldPanel.add(cityNameFieldLabel);
		createCustomFields(cityNameNumberField, cityNameNumberFieldPanel);
		signUpFormPanel.add(cityNameNumberFieldPanel, constraints);

		constraints.insets = new Insets(10, 0, 0, 0);
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;

		JButton signUpButton = new JButton("Sign Up");
		createCustomJButton(signUpButton);
		signUpButtonPanel.add(signUpButton);
		signUpFormPanel.add(signUpButtonPanel, constraints);

		signUpFormPanel.add(Box.createVerticalGlue());

	}

	public JPanel getSignUpPagePanel() {
		return signUpPagePanel;
	}

}
