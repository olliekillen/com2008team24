import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JPanel;

public class HomePageUI extends UI {

	JPanel homePagePanel = new JPanel(new BorderLayout());

	public HomePageUI() {
	}

	@Override
	protected void formatNavBar(JPanel navBar) {
		setNavBarLayout(navBar);
		navBar.setBackground(Color.blue);
		navBar.add(Box.createHorizontalGlue());
		navBar.add(goToSignUpPageButtonPanel);
		navBar.add(goToLoginPageButtonPanel);
	}

	@Override
	protected void generatePage() {
		JPanel homePageNavBar = new JPanel();
		formatNavBar(homePageNavBar);
		homePagePanel.add(homePageNavBar, BorderLayout.NORTH);

	}

	public void displayInitialPage() {
		generalFrame.setContentPane(homePagePanel);
	}

	public JPanel getHomePagePanel() {
		return homePagePanel;
	}

}
