package com.comast.crm.contacttest;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTestAM {

	public static void main(String[] args) throws Throwable {
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
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		
	
		String ContactLastName = eLib.getDataFromExcel("contact",7,3);
	

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
		wLib.waitForPageToLoad(driver);
	   driver.get(URL);
	   
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 2:navigate to organization
		driver.findElement(By.linkText("Organizations")).click();

		// Step 3:click on create organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 4:enter all details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// Verify Header msg and Expected Result

		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (headerInfo.contains(orgName))// contains function in java we can verify partial data
		{
			System.out.println(orgName + "header is verified==PASS");
		} else {
			System.out.println(orgName + "header is verified==FAIL");
		}

		// Step 5:navigate to organization page
		driver.findElement(By.linkText("Contacts")).click();

		// step 6:click on create organization button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 7:enter all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(ContactLastName);

		// click on lookup window for orgname
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		Thread.sleep(3000);
		// switch to child window
		String parentWindow = driver.getWindowHandle();
		Set<String> set1 = driver.getWindowHandles();// set is random list,data is not stored in order
		
		
		// switch to child window
		
		wLib.switchToTabOnTitle(driver, "module=Accounts");
		
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		Thread.sleep(2000);
		
		// switch back to parent window
		wLib.switchToTabOnTitle(driver, "module=Contacts");
		
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// Verify contact lastname with Expected Result

		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (headerInfo.contains(ContactLastName))// contains function in java we can verify partial data
		{
			System.out.println(ContactLastName + "is created==PASS");
		} else {
			System.out.println(ContactLastName + "is  not created==FAIL");
		}

		// Verify Header orgName and Expected Result

		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgName);
		if (actOrgName.trim().equals(orgName))// contains function in java we can verify partial data
		{
			System.out.println(orgName + "is created==PASS");
		} else {
			System.out.println(orgName + "is  not created==FAIL");
		}

		// step 5:logout

		// actOrgNamedriver.quit();

	}

}
