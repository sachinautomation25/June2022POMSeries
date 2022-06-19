package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pojo.Contacts;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ElementUtils;
import com.qa.trcrm.utils.JavaScriptUtil;

public class ContactsPage extends BasePage {

	WebDriver driver;
	ElementUtils util;
	JavaScriptUtil jsUtil;
	
	By contactPageHeader=By.xpath("(//h2[text()])[1]");
	
	//By addPersonBtn=By.cssSelector("button.hidden-xs.hidden-sm.btn.btn-danger.mr5.ng-scope.ng-binding");
	By addPersonBtn = By.xpath("//button[@class='hidden-xs hidden-sm btn btn-danger mr5 ng-scope ng-binding']");
	By name=By.name("name");
	By email=By.id("email0");
	
	//By saveBtn=By.cssSelector("button.btn.btn-primary.btn-large.ng-binding");
	By saveBtn=By.xpath("//button[@class='btn btn-primary btn-large ng-binding']");
	
	By personAddedMsg=By.xpath("//span[text()='Person added.']");
	
	public ContactsPage(WebDriver driver)
	{
		this.driver=driver;
		util=new ElementUtils(driver);
		jsUtil=new JavaScriptUtil(driver);
	}
	
	public String verifyContactPageHeader() {
		util.waitForElementPresent(contactPageHeader);
		return util.doGetText(contactPageHeader);
	}
	public String addPerson(String firstName,String emailId) {
		util.waitForElementPresent(addPersonBtn);
		util.doClick(addPersonBtn);
		util.waitForElementPresent(name);
		util.doSendKeys(name, firstName);
		util.doActionSendKeys(email, emailId);
		
		util.doClick(saveBtn);
		
		util.waitForElementPresent(personAddedMsg);
		
		return util.doGetText(personAddedMsg);
	}
	public String addPerson(Contacts contacts) {
		util.waitForElementPresent(addPersonBtn);
		util.doClick(addPersonBtn);
		util.waitForElementPresent(name);
		util.doSendKeys(name, contacts.getFirstName());
		util.doActionSendKeys(email, contacts.getEmailId());
		
		//util.doClick(saveBtn);
		jsUtil.clickElementByJS(util.getElement(saveBtn));
		
		util.waitForElementPresent(personAddedMsg);
		
		return util.doGetText(personAddedMsg);
	}
}
