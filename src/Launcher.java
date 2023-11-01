
public class Launcher {

	public static void main(String[] args) {

		HomePageUI homePage = new HomePageUI();
		homePage.setFrameProperties();
		homePage.addListenersToPageChangingButtons();
		homePage.addComponentsToPanels();
		homePage.generatePage();
		homePage.displayInitialPage();

	}

}
