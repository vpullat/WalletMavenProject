package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WalletHubLoginPage extends BasePage {

	public WalletHubLoginPage(WebDriver driver) {
		super(driver);
	}
	
	private WebDriver driver = super.driver;
	private By textFieldUserName = By.id("email");
	private By textFieldPassword = By.id("password");
	private By buttonLogin = By.cssSelector(".btn.blue.center");
	private By linkLoginPageLogo = By.id("logo-link");

	// Login to wallethub
	public WalletHubHomePage loginToApp(String user, String password) {

		try {
			waitForElement(linkLoginPageLogo);
			enterText(textFieldUserName, user);
			enterText(textFieldPassword, password);
			click(buttonLogin);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonLogin));

		} catch (Exception e) {
			return null;
		}
		return new WalletHubHomePage(driver);
	}

}
