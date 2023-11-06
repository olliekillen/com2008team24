
public class Launcher {

	public static void main(String[] args) {

		HomePageUI homePage = new HomePageUI();
		homePage.addListenersToPageChangingButtons();
		homePage.addComponentsToPanels();
		homePage.generatePage();
		homePage.displayInitialPage();
		homePage.setFrameProperties();

	}

}
