package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHomePage extends BasePage {

	public UserHomePage(WebDriver driver) {
		super(driver);
	}
	
	private By labelUserHomePage = By.cssSelector("h1.gmql0nx0.l94mrbxd.p1ri9a11.lzcic4wl");
	private By inputWhatsOnMind = By.cssSelector("div.m9osqain.a5q79mjw.gy2v8mqq.jm1wdb64.k4urcfbm.qv66sw1b");
	private By inputWritePost = By.cssSelector("p.i1ao9s8h.hcukyx3x.oygrvhab.cxmmr5t8.kvgmc6g5");
	private By buttonPost = By.cssSelector("div.k4urcfbm.discj3wi.dati1w0a.hv4rvrfc.i1fnvgqd.j83agx80.rq0escxv.bp9cbjyn div.bp9cbjyn.j83agx80.taijpn5t.c4xchbtz");
	private By dialogCreatePost = By.cssSelector("div.j83agx80.cbu4d94t.f0kvp8a6.mfofr4af.l9j0dhe7.oh7imozk.ij1vhnid.smbo3krw");
	
	private By getPostLocator(String post) {
		
		return By.xpath("//div[@class = 'kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x c1et5uql' and text()='"+post+"']");
	}
	
	// Wait for the homepage to load
	public String waitHomePageLoad() {
		String isPageLoaded = "Success";

		try {
			waitForElement(labelUserHomePage);
		} catch (Exception e) {
			isPageLoaded="Homepage load failed:"+e;
		}
		return isPageLoaded;
	}
	
	// Post status of the user
	public String postStatus(String post) {		
		try {
			click(inputWhatsOnMind);
			waitForElement(inputWritePost);
			enterText(inputWritePost,post);
			click(buttonPost);
			waitForElementToDisappear(dialogCreatePost);
		} catch (Exception e) {
			return "Posting status failed -" + e;
		}
		return "Success";
	}
	
	public String verifypostIsUpdated(String post) {
		
		By postLocator = getPostLocator(post);
		try {
			waitForElement(postLocator);
		} catch (Exception e) {
			return "Verifying post failed -" + e;
		}
		return "Success";
	}

}
