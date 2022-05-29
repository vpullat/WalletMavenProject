package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.WalletHubHomePage;
import pageObjects.WalletHubProfilePage;
import utilityPackage.UtilityClass;

public class WalletHubTest extends BaseTest {

	@Test
	public void fnReviewInsurance() {
		WalletHubHomePage objWalletHomePage = super.objWalletHomePage;
		String strReviewText = null;
		String strItemToReview = null;
		String strReviewingUserName = null;

		// Get test data from testData json file
		try {
			JSONObject objJson = UtilityClass.readDataFile("testdata");
			strReviewText = (String) objJson.get("ReviewText");
			strItemToReview = (String) objJson.get("ReviewItem");
			strReviewingUserName = (String) objJson.get("ReviewUserName");
		} catch (Exception e) {
			System.out.println("Exception occured while reading test data:" + e);

		}

		try {
			// Wait for Homepage to load
			String strHomePageLoad = objWalletHomePage.waitForHomePageLoad();

			if (!strHomePageLoad.equals("Success")) {

				System.out.println("Loadng Homepage page failed");
				Assert.assertTrue(false, "Loadng Homepage page failed: " + strHomePageLoad);
			}
			System.out.println("Wallethub Homepage successfully loaded");

			// Give a 4 star rating in Reviews section
			String strRating = objWalletHomePage.giveStarRating(4);

			System.out.println(strRating);
			if (!strRating.equals("Success")) {

				System.out.println("Giving a start rating failed");
				Assert.assertTrue(false, "Giving a start rating failed: " + strRating);
			}
			System.out.println("Successfully gave a 4 star rating");

			// Submit the rating and the review for Health Insurance and verify the
			// confirmation page is displayed
			String strReviewPostSuccessMessage = objWalletHomePage.submitReviewAndVerifySuccess(strReviewText,
					strItemToReview);

			if (!strReviewPostSuccessMessage.contains("review has been posted")) {

				System.out.println("Rating and review submit failed");
				Assert.assertTrue(false, "Rating and review submit failed: " + strReviewPostSuccessMessage);
			}
			System.out.println("Successfully submitted review for Health Insurance. Confirmation is displayed");

			
			// Navigate to user profile page and check review is updated
			WalletHubProfilePage objProfilePage = objWalletHomePage.navigateToProfilePage();	
			
			String strReviewUpdatedInProfilePage = objProfilePage.verifyReviewInProfilePage();
			
			if (!strReviewUpdatedInProfilePage.equals("Success")) {

				System.out.println("Review not updated in profile page");
				Assert.assertTrue(false, "Review update in Profile page failed: " + strReviewUpdatedInProfilePage);
			}	
			System.out.println("Successfully verified review in profile page");

			// Verify the review is posted in the Reviews section
			String strIsReviewPostedInReviewsSection = objWalletHomePage.verifyReviewInReviewsSection(strReviewingUserName);

			if (!strIsReviewPostedInReviewsSection.equals("Success")) {

				System.out.println("Verification of posted review in Reviews section failed");
				Assert.assertTrue(false, "Posted review verification failed: " + strIsReviewPostedInReviewsSection);
			}
			System.out.println("Successfully verified review in Reviews section");

		} catch (Exception e) {
			System.out.println("Review insurance test failed");
			Assert.assertTrue(false, "Test Failed with exception: " + e);
		}
	}
}
