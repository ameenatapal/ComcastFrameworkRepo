package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	WebDriver driver;
	public Homepage(WebDriver driver)        //Rule3-Object Intialization 
	{
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText="Compaigns")
	private WebElement compaignlnk;
	
	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG' and @border='0']") //admin img
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")       //signout
	private WebElement signOutLnk;
	
	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	public WebElement getAdminImg() {
		return adminImg;
	}

	
	
	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}
	
	public void navigateToCompaignPage()   //business link
	{
		Actions act=new Actions(driver);
		act.moveToElement(moreLink).perform();
		compaignlnk.click();
	}
	
	public void logout() throws InterruptedException                   //sign out
	{
		Actions act=new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(adminImg).perform();
		Thread.sleep(2000);
		signOutLnk.click();
	}
	
	
	

}
