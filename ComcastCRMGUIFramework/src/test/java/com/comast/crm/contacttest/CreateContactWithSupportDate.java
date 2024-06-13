package com.comast.crm.contacttest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws Throwable {
		//Create Object
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		
		//read data from fileutility
		
		String BROWSER=fLib.getDataFromPropertiesFile("browser");
		String URL=fLib.getDataFromPropertiesFile("url");
		String USERNAME=fLib.getDataFromPropertiesFile("username");
		String PASSWORD=fLib.getDataFromPropertiesFile("password");
	
		//read test script data from excel utility and get random num from java utility
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();
			
       // PolyMorphism program,so it can run with different browser

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
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 2:navigate to contacts
		driver.findElement(By.linkText("Contacts")).click();

		// Step 3:click on create create button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 4:enter all details and create new contact
         
		String startDate = jLib.getSystemDateyyyyDDMM();
		String endDate = jLib.getRequiredDateyyyyDDMM(30);
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
      driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// Verify Header phone numb and Expected Result
		//(start date)
		String actStartDate= driver.findElement(By.id("dtlview_Support Start Date")).getText();

		if (actStartDate.equals(startDate))// contains function in java we can verify partial data
		{
			System.out.println(startDate + "is created==PASS");
		} else {
			System.out.println(startDate + "is  not created==FAIL");
		}
		
		
		//(end date)
		String actendDate= driver.findElement(By.id("dtlview_Support End Date")).getText();

				if (actendDate.equals(endDate))// contains function in java we can verify partial data
				{
					System.out.println(endDate + "is created==PASS");
				} else {
					System.out.println(endDate + "is  not created==FAIL");
				}
		// step 5:logout
		driver.quit();

	}
}
