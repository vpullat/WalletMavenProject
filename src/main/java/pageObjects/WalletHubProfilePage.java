package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WalletHubProfilePage extends BasePage {
	
	

	public WalletHubProfilePage(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait = super.wait;

	private By labelRatingStarsInProfile = By.cssSelector(".inst-rating.rvs-plain.rvs-svg ");
	private By labelInsuranceCompany = By.xpath("//a[text()='Test Insurance Company']");
	
	// Check review is updated in the profile of the user
	public String verifyReviewInProfilePage() {

		String strUpdate = "Success";
		try {
			waitForElement(labelRatingStarsInProfile);
			waitForElement(labelInsuranceCompany);
			click(labelInsuranceCompany);

		} catch (Exception e) {

			strUpdate = "Failed";
		}
		return strUpdate;
	}

}
