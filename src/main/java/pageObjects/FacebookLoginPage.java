package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FacebookLoginPage extends BasePage {

	public FacebookLoginPage(WebDriver driver) {
		super(driver);
	}

	private WebDriver driver = super.driver;
	private By textFieldUsername = By.id("email");
	private By textFieldPassword = By.id("pass");
	private By buttonLogin = By.cssSelector("button[name='login']");

	// Login to Facebook
	public FacebookWelcomePage loginToApp(String user, String password) {

		try {
			enterText(textFieldUsername, user);
			enterText(textFieldPassword, password);
			click(buttonLogin);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonLogin));

		} catch (Exception e) {
			return null;
		}
		return new FacebookWelcomePage(driver);
	}

}
