package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	WebDriver driver;

	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "dtlview_Support Start Date")
	private WebElement headerPhone;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getHeaderPhone() {
		return headerPhone;
	}

	
	
}
