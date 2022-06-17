package com.qa.trcrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
		action = new Actions(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("some exception occured while creating the web element: " + locator);
		}
		return element;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	

	public String doGetTitle() {
		return driver.getTitle();
	}

	public void waitForElementPresent(By locator) {
		// WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public String doGetPageTitle(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public void doActionClick(By locator) {
		// Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}

	public void doActionSendKeys(By locator, String value) {
		// Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).build().perform();
	}

	public void doMoveToElement(By locator) {
		action.moveToElement(getElement(locator)).build().perform();
	}
	public void doClear(By locator) {
		getElement(locator).clear();
	}

}
