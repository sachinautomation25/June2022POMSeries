package com.qa.trcrm.pages;

import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;

public class ProfilePage2 extends BasePage {

	WebDriver driver;

	public ProfilePage2(WebDriver driver) {
		this.driver = driver;
	}

	public void remote() {
		System.out.println("ProfilePage--remote");
		System.out.println("someone merged this statement after pr process");
	}

	public void local()// local changes
	{
		System.out.println("ProfilePage--local");
		System.out.println("ProfilePage--local");
	}
}
