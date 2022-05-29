package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookWelcomePage extends BasePage {

	public FacebookWelcomePage(WebDriver driver) {
		super(driver);
	}

	private WebDriver driver = super.driver;
	private By labelWelcome = By
			.xpath("//span[contains(@class, 'd2edcug0 hpfvmrgz') and contains(text(),'Welcome to Facebook')]");
	private By linkUser = By.cssSelector("div.fop5sh7t.fv0vnmcu.j83agx80");

	public String waitWelcomePageLoad() {
		String isPageLoaded = "Success";

		try {
			waitForElement(labelWelcome);
		} catch (Exception e) {
			isPageLoaded = "Failed:" + e;
		}
		return isPageLoaded;
	}

// Method to navigate to homepage of logged in user
	public UserHomePage goToUserHomePage() {

		try {

			click(linkUser);
			waitTillPageIsLoaded(driver);

		} catch (Exception e) {

			return null;
		}
		return new UserHomePage(driver);
	}

}
