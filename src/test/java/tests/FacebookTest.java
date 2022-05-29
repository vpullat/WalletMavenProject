package tests;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.FacebookWelcomePage;
import pageObjects.UserHomePage;
import utilityPackage.UtilityClass;

public class FacebookTest extends BaseTest {

	WebDriver driver = super.driver;

	@Test
	public void postFacebookStatus() {

		FacebookWelcomePage objWelcome = super.objWelcome;
		String strFacebookPost = null;

		try {
			JSONObject objJson = UtilityClass.readDataFile("testdata");
			strFacebookPost = (String) objJson.get("FacebookData");
		} catch (Exception e) {
			System.out.println("Exception occured while reading test data:" + e);

		}

		try {
			String strWelcomePage = objWelcome.waitWelcomePageLoad();
			if (!strWelcomePage.equals("Success")) {

				System.out.println("Loading welcome page failed");
				Assert.assertTrue(false, strWelcomePage);
			}
			System.out.println("Navigated to facebook welcome page");

			// Click user profile link and navigate to homepage
			UserHomePage objHome = objWelcome.goToUserHomePage();

			String strHomePage = objHome.waitHomePageLoad();

			if (!strHomePage.equals("Success")) {

				System.out.println("Loading User Home page failed");
				Assert.assertTrue(false, strHomePage);
			}
			System.out.println("Navigated to facebook Homepage");

			// Post a status
			String strPostStatus = objHome.postStatus(strFacebookPost);

			if (!strPostStatus.equals("Success")) {
				System.out.println("Posting status failed");
				Assert.assertTrue(false, "Posting status in facebook failed-" + strPostStatus);
			}
			System.out.println("Successfully posted status");

			// Verify the post is updated
			String strPostVerify = objHome.verifypostIsUpdated(strFacebookPost);

			if (!strPostVerify.equals("Success")) {
				System.out.println("Verification of post failed");
				Assert.assertTrue(false, "Post cannot be verified-" + strPostVerify);
			}
			System.out.println("Successfully verified the status");

		} catch (Exception e) {
			System.out.println("Post Facebook status test failed");
			Assert.assertTrue(false, "Test Failed with exception: " + e);
		}
	}
}
