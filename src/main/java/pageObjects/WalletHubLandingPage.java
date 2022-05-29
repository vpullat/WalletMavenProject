package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WalletHubLandingPage extends BasePage{
	
	public WalletHubLandingPage (WebDriver driver) {
		super(driver);
	}
	
	private WebDriver driver = super.driver;
	private By linkLogin = By.cssSelector(".brgm-button.brgm-signup.brgm-signup-login");
	
	
	// Navigate to wallet hub login page
	public WalletHubLoginPage navigateToLoginPage() {		
		try {
			click(linkLogin);
			waitForElementToDisappear(linkLogin);
		} catch (Exception e) {
			return null;
		}
		return new WalletHubLoginPage(driver);
	}

}
