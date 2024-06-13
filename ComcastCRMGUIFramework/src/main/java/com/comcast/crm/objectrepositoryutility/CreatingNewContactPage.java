package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver)        //Rule3-Object Intialization 
	{
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnametxt;
	
	@FindBy(name="button")
	private WebElement savebtn;
	

	@FindBy(name="support_start_date")
	private WebElement supportstartdate;
	
	@FindBy(name="support_end_date")
	private WebElement supportenddate;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getLastnametxt() {
		return lastnametxt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void CreateContact(String lastName)
	{
		lastnametxt.sendKeys(lastName);
		savebtn.click();
	}
	

	public WebElement getSupportstartdate() {
		return supportstartdate;
	}

	public WebElement getSupportenddate() {
		return supportenddate;
	}
	
	public void createContactWithSupportDate(String lastname,String startdate,String enddate)
	{
		lastnametxt.sendKeys(lastname);
		supportstartdate.clear();
		supportstartdate.sendKeys(startdate);
		
		supportenddate.clear();
		supportenddate.sendKeys(enddate);
		
		savebtn.click();
		}
	
	

}
