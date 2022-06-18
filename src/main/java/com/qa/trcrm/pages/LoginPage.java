package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pojo.Credentials;
import com.qa.trcrm.utils.ElementUtils;
import com.qa.trcrm.utils.JavaScriptUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtils util;
	JavaScriptUtil jsUtil;

	// 1. Page objects-By locators
	By username = By.id("_username");
	By password = By.id("_password");
	By loginBtn = By.xpath("//input[@type='submit']");

	By signUpNowLink = By.linkText("Sign Up Now");
	
	By errorMsg=By.id("error");
	By userLogin=By.xpath("//h3[text()='User Login']");
	
	By random=By.id("random");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtils(driver);
		jsUtil=new JavaScriptUtil(driver);

	}

	// 2. Page actions/methods
	public String getPageTitle() {
		return util.doGetTitle();
	}

	public boolean verifySignUpLInk() {
		return util.isDisplayed(signUpNowLink);
	}

	public HomePage doLogin(Credentials credentials) {
		util.doClear(username);
		util.doSendKeys(username, credentials.getEmailId());
		util.doClear(password);
		util.doSendKeys(password, credentials.getPassword());
		util.doClick(loginBtn);

		return new HomePage(driver);
	}
	public HomePage doLogin(String emailId,String pwd) {
		util.doClear(username);
		
		util.doSendKeys(userLogin, emailId);
		util.doClear(password);
		util.doSendKeys(password, pwd);
		util.doClick(loginBtn);
		
		return new HomePage(driver);
		
	}
	public boolean errorMessage() {
		return util.isDisplayed(userLogin);
	}
}
