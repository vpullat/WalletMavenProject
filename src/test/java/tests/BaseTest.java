package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.FacebookLoginPage;
import pageObjects.FacebookWelcomePage;
import pageObjects.WalletHubHomePage;
import pageObjects.WalletHubLandingPage;
import pageObjects.WalletHubLoginPage;
import utilityPackage.UtilityClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class BaseTest {

	protected WebDriver driver;
	protected FacebookWelcomePage objWelcome = null;
	protected WalletHubHomePage objWalletHomePage = null;

	@BeforeTest
	@Parameters("app")
	public void setup(String app) {
		String appURL = null;
		String strUserName = null;
		String strPassword = null;
		JSONObject objJson = null;

		try {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(capabilities);

			driver = new ChromeDriver(options);

			// Open the browser and navigate to the url according to the test environment
			try {
				objJson = UtilityClass.readDataFile("configdata");

				switch (app) {

				case "facebook":
					appURL = (String) objJson.get("facebookurl");
					strUserName = (String) objJson.get("usernamefacebook");
					strPassword = (String) objJson.get("passwordfb");
					driver.get(appURL);
					FacebookLoginPage objLogin = new FacebookLoginPage(driver);
					objWelcome = objLogin.loginToApp(strUserName, strPassword);
					System.out.println("Successfully logged into facebook");
					break;

				case "wallethub":
					appURL = (String) objJson.get("wallethuburl");
					strUserName = (String) objJson.get("wallethubuser");
					strPassword = (String) objJson.get("wallethubpassword");
					driver.get(appURL);
					WalletHubLandingPage objWalletHubLanding = new WalletHubLandingPage(driver);

					WalletHubLoginPage objWalletHubLogin = objWalletHubLanding.navigateToLoginPage();
					objWalletHubLogin.waitTillPageIsLoaded(driver);

					objWalletHomePage = objWalletHubLogin.loginToApp(strUserName, strPassword);
					System.out.println("Successfully logged into wallethub");
					break;
				}

			} catch (Exception e) {
				System.out.println("Login failed with exception");
				Assert.assertTrue(false, "Login failed:" + e);
			}

		} catch (Exception e) {
			System.out.println("Login failed failed");
			Assert.assertTrue(false, "Login failed:" + e);
		}
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
