package com.qa.trcrm.pages;

import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;

public class ProfilePage extends BasePage {

	WebDriver driver;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
	}
  public void remote()
  {
  System.out.println("ProfilePage--remote");
  }
}
