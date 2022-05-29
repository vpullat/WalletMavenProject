package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WalletHubHomePage extends BasePage {

	public WalletHubHomePage(WebDriver driver) {
		super(driver);
	}

	private WebDriver driver = super.driver;
	WebDriverWait wait = super.wait;

	private By labelHeaderLogo = By.cssSelector("div.top-header-logo");
	private By linkReviews = By.xpath("//span[@class='nav-txt' and text()='Reviews']");
	private By labelRatingStar = By.cssSelector(".rv.review-action .rvs-svg svg.rvs-star-svg");
	private By labelWhatsYourRating = By.cssSelector("h3.rsba-h3.bold-font");
	private By dropDwonSelect = By.cssSelector(".wrev-drp .dropdown-placeholder");
	private By textBoxWriteReview = By.cssSelector(".textarea.wrev-user-input.validate");
	private By buttonSubmit = By.cssSelector("div.sbn-action.semi-bold-font.btn.fixed-w-c.tall");
	private By labelReviewPostedMessage = By.xpath("//h4[text()='Your review has been posted.']");
	private By buttonContinue = By.cssSelector("div.btn.rvc-continue-btn");
	private By labelTestInsurance = By.cssSelector("h4.wrev-prd-name");
	private By labelUserNameOnHeader = By.cssSelector("div.brgm-button.brgm-user.brgm-list-box .brgm-list-title");
	private By linkProfile = By.xpath("//div[@class='brgm-button brgm-user brgm-list-box']//a[text()='Profile']");

	private static By getDropDownValueSelector(String selectValue) {

		return By.xpath("//li[text()='" + selectValue + "']");
	}

	private static By getPostedReviewLocator(String userName) {

		return By.xpath("//span[@class='rvtab-ci-nickname regular-font' and text()='" + userName + "']");
	}

	public String waitForHomePageLoad() {
		try {
			waitForElement(labelHeaderLogo);
		} catch (Exception e) {
			return "Homepage not loaded -" + e;
		}
		return "Success";
	}

	// Hover over stars and give rating
	public String giveStarRating(int rating) {

		String isStartLitUp = "Success";
		boolean isStarUpdated;
		int itrStartUpdate = 0;
		Actions objAction = new Actions(driver);

		try {
			click(linkReviews);
			waitForElement(labelWhatsYourRating);
			List<WebElement> elementName = getAllElements(labelRatingStar);

			for (int itr = 0; itr < elementName.size() - 1; itr++) {
				isStarUpdated = false;

				while (!isStarUpdated && itrStartUpdate < 10) {
					try {
						objAction.moveToElement(elementName.get(itr)).perform();
						wait.until(ExpectedConditions.attributeToBe(elementName.get(itr), "aria-checked", "true"));
						if (itr == (rating - 1)) {
							click(elementName.get(itr));
						}
						isStarUpdated = true;
					} catch (Exception e) {
						itrStartUpdate++;
						if(itrStartUpdate==10) {
							throw e;
						}
					}
				}
			}
		} catch (Exception e) {
			isStartLitUp = "Star click failed " + e;
		}
		return isStartLitUp;
	}

	// Submit review and check the confirmation
	public String submitReviewAndVerifySuccess(String review, String selectOption) {

		String strUpdate = null;
		By optionToSelect = getDropDownValueSelector(selectOption);
		try {
			waitForElement(labelTestInsurance);
			click(dropDwonSelect);
			click(optionToSelect);
			enterText(textBoxWriteReview, review);
			click(buttonSubmit);
			waitForElement(labelReviewPostedMessage);
			strUpdate = getText(labelReviewPostedMessage);
			click(buttonContinue);

		} catch (Exception e) {
			strUpdate = "review submission failed " + e;
		}
		return strUpdate;
	}

	// Check the review is added to the Reviews section
	public String verifyReviewInReviewsSection(String username) {

		String strUpdate = "Success";
		By userName = getPostedReviewLocator(username);
		try {
			waitForElement(labelHeaderLogo);
			waitForElement(userName);

		} catch (Exception e) {

			strUpdate = "Review verification failed " + e;
		}
		return strUpdate;
	}

	// Navigate to profile page of the user
	public WalletHubProfilePage navigateToProfilePage() {

		boolean isClicked = false;
		int itrClick = 0;
		Actions objAction = new Actions(driver);
		WebElement userNameOnHeader = driver.findElement(labelUserNameOnHeader);

		try {
			waitForElement(labelUserNameOnHeader);
			while (!isClicked && itrClick < 10) {
				try {
					objAction.moveToElement(userNameOnHeader).perform();
					click(linkProfile);
					isClicked = true;
				} catch (Exception e) {
					itrClick++;
					if(itrClick ==10) {
						throw e;
					}
				}
			}
			waitForElement(labelUserNameOnHeader);
		} catch (Exception e) {

			return null;
		}
		return new WalletHubProfilePage(driver);
	}
}
