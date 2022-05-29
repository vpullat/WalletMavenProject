package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 15);

	}

	public void waitTillPageIsLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		wait.until(pageLoadCondition);
	}

	public void click(By elementBy) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementBy));
			driver.findElement(elementBy).click();
		} catch (Exception e) {
			throw e;
		}
	}

	public void click(WebElement elementBy) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementBy));
			elementBy.click();
		} catch (Exception e) {
			throw e;
		}
	}

	public void waitForElement(By elementBy) {

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
			wait.until(ExpectedConditions.elementToBeClickable(elementBy));

		} catch (Exception e) {
			throw e;
		}
	}

	public void waitForElementToDisappear(By element) {

		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));

		} catch (Exception e) {
			throw e;
		}
	}

	public List<WebElement> getAllElements(By elementBy) {

		try {
			List<WebElement> elementName = driver.findElements(elementBy);
			return elementName;
		} catch (Exception e) {
			return null;
		}

	}

	public String getText(By elementBy) {

		String strText = "";
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
			strText = driver.findElement(elementBy).getText();

		} catch (Exception e) {
			return strText;
		}
		return strText;
	}

	public void enterText(By elementBy, String textToType) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementBy));
			driver.findElement(elementBy).sendKeys(textToType);

		} catch (Exception e) {
			throw e;
		}
	}
}
