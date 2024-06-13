package com.comast.crm.orgtest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.Homepage;
import com.comcast.crm.objectrepositoryutility.Loginpage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.Organizationpage;

public class DeleteOrgTest {

	public static void main(String[] args) throws Throwable {
		
		//This Tc is automated using POM design technique
        //Create Object
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//read data from fileutility
		
		String BROWSER=fLib.getDataFromPropertiesFile("browser");
		String URL=fLib.getDataFromPropertiesFile("url");
		String USERNAME=fLib.getDataFromPropertiesFile("username");
		String PASSWORD=fLib.getDataFromPropertiesFile("password");
	
		//read test script data from excel utility and get random num from java utility
		String orgName = eLib.getDataFromExcel("org",10,2) + jLib.getRandomNumber();
			
		

		// PolyMorphism program.In Properties file if you change the browser name
		// so it can run with different browser

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		// Step 1: Login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		//Loginpage lp = PageFactory.initElements(driver,Loginpage.class);
		Loginpage lp=new Loginpage(driver);
		lp.loginToapp(URL,USERNAME,PASSWORD);
		

		// Step 2:navigate to organization
		Homepage hp=new Homepage(driver);
		hp.getOrgLink().click();
		
        Thread.sleep(2000);
		// Step 3:click on create organization plus button
		Organizationpage op=new Organizationpage(driver);
		op.getCreateNewOrgbtn().click();
		
		
		// Step 4:enter all details and create new organization
		CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
		cnp.createOrg(orgName);
		
		//verify Header msg Expected Result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName))
		{
			System.out.println(orgName+"name is verified==PASS");
		}
		else
		{
			System.out.println(orgName+"name is not verified==FAIL");
		}
		
		//go back to Organizations Page
		
		hp.getOrgLink().click();
		
		//search for Organization
		op.getSearchEdt().sendKeys(orgName); 
		wLib.select(op.getSearchDD(),"Organization Name");//select organization name from drop down
		op.getSearchBtn().click();
		
		//dynamic orgName concatenate and this is called dynamic path getting constructed in runtime 
		//driver.findElement(By.xpath("(//a[text()='"+orgName+"']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../..//a[text()='del']")).click();

		wLib.switchToAlertAndAccept(driver);
		
		//In dynamic webtable select and delete org
		
       hp.logout();
		driver.quit();
}
       }
	
