package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class Loginpage extends WebDriverUtility {  //Rule1-create separate java class
	    
	WebDriver driver;
		public Loginpage(WebDriver driver)        //Rule3-Object Intialization 
		{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		}
	@FindBy(name="user_name")         	//Rule2-Object Creation
	public WebElement usernameEdt;
	
	@FindBy(name="user_password")
	public WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	public WebElement loginBtn;
	
   public WebElement getUsernameEdt()    //Rule4-Object Encapsulation
                                          //Object Utilization
   {
	   return usernameEdt;
   }
	
   public WebElement getPasswordEdt()
   {
	   return passwordEdt;
   }
   
   public WebElement getLoginBtn()
   {
	   return loginBtn;
   }
   
   public void loginToapp(String url,String username,String password)
   {
	   waitForPageToLoad(driver);
	   driver.get(url);
	   driver.manage().window().maximize();
	   usernameEdt.sendKeys(username);
	   passwordEdt.sendKeys(password);
	   loginBtn.click();
   }
   }

