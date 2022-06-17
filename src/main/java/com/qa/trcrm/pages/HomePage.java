package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ElementUtils;
import com.qa.trcrm.utils.JavaScriptUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtils util;
	JavaScriptUtil jsUtil;

	//By homePageHeader = By.xpath("//span[text()='Homepage']");
	By homePageHeader = By.xpath("//span[text()='Homepage']");
	By loggedInUser = By.xpath("//span[text()=' sachin sharma']");
	
	By contactPage=By.xpath("//li[@id='contactMenuLi']/a");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtils(driver);
		jsUtil=new JavaScriptUtil(driver);
		
	}

	public String getHomePageTitle() {
		return util.doGetPageTitle(AppConstants.HOME_PAGE_TITLE);
	}

	public String isHomePageHeaderExist() {
		//util.waitForElementPresent(homePageHeader);
		if (util.isDisplayed(homePageHeader)) {
			return util.doGetText(homePageHeader);
		}
		return null;

	}
	
	

	public String isUserLoggedIn() {
		util.waitForElementPresent(loggedInUser);
		if (util.isDisplayed(loggedInUser)) {
			System.out.println("util.idDisplayed(loggedInUser)");
			return util.doGetText(loggedInUser);
		}
		return null;
	}
	public ContactsPage contactPage() {
		util.waitForElementPresent(contactPage);
		//util.doClick(contactPage);
		
	jsUtil.clickElementByJS(util.getElement(contactPage));
		
		return new ContactsPage(driver);
	}

}
