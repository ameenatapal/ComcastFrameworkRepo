package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver)        
	{
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getIndustryDB() {
		return industryDB;
	}


	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDB;
	
	

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName)
	{
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
		}
	
	public void createOrg(String orgName,String industry)
	{
		orgNameEdt.sendKeys(orgName);
		Select sel=new Select(industryDB);
		sel.selectByVisibleText(industry);
		saveBtn.click();
		}
	
}
