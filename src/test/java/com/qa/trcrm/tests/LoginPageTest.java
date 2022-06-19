package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.pojo.Credentials;
import com.qa.trcrm.utils.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic-101 : define login feature for trcrm application")
@Feature("US-1001 :create features for login with signup,title and login")
public class LoginPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials credentials;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		credentials=new Credentials(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Description("verify login page title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Description("verify sign up link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLInk());
	}

	@Description("verify login test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void loginTest() {
		homePage = loginPage.doLogin(credentials);
		Assert.assertEquals(homePage.isHomePageHeaderExist(), AppConstants.HOME_PAGE_HEADER);
	}

	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][] = { { "test@gmail.com", "test@123" }, { "test123@gmail.com", "pass123" }, { "", "" }

		};
		return data;
	}

	@Test(priority = 4, dataProvider = "getLoginInvalidData", enabled = false)
	public void login_InvalidTestCases(String email, String pwd) {
		credentials=new Credentials(email, pwd);
		loginPage.doLogin(credentials);
		Assert.assertTrue(loginPage.errorMessage());
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
