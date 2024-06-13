package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizationpage {
	WebDriver driver;
	public Organizationpage(WebDriver driver)        //Rule3-Object Intialization 
	{
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}

	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createNewOrgbtn;

	public WebElement getCreateNewOrgbtn() {
		return createNewOrgbtn;	
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

}
