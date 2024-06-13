package com.comast.crm.orgtest;


import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgWithPhoneNumberTest {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fisl=new FileInputStream("./configAppData/commondata.properties");
		Properties pObj=new Properties();
		pObj.load(fisl);
		
		
		String BROWSER=pObj.getProperty("browser");
		String URL=pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username");
		String PASSWORD=pObj.getProperty("password");
		
		
	//generate Random number 
			     Random random=new Random();
				int randomInt=random.nextInt(1000);
				
				//read test script data from excel file
				FileInputStream fis1=new FileInputStream("./testdata/TestScriptData1.xlsx");
				Workbook wb=WorkbookFactory.create(fis1);
				Sheet sh=wb.getSheet("contact");
				Row row=sh.getRow(1);
				String lastName = row.getCell(2).toString()+randomInt;
				
			
				wb.close();
				
		//PolyMorphism program.In Properties file if you change the browser name
		//so it can run with different browser
				
				WebDriver driver=null;
				if(BROWSER.equals("chrome"))
				{
					driver=new ChromeDriver();
			}else if(BROWSER.equals("firefox"))
				{
				driver=new FirefoxDriver();
				}else if(BROWSER.equals("edge"))
				{
					driver=new EdgeDriver();
					}else
					{
						driver=new ChromeDriver();
					}
				
				
				//Step 1: Login to app
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				driver.get(URL);
			   driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//Step 2:navigate to contacts
				driver.findElement(By.linkText("Contacts")).click();
				
				//Step 3:click on create create button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//Step 4:enter all details and create new contact
				driver.findElement(By.name("lastname")).sendKeys(lastName);
				
				
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				//Verify Header msg and Expected Result
				

				String actLastName= driver.findElement(By.id("dtlview_Last Name")).getText();
				
				if(actLastName.equals(lastName))//contains function in java we can verify partial data 
				{
					System.out.println(lastName + "is created==PASS");
				}else {
					System.out.println(lastName + "is  not created==FAIL");
	            }
				
				
				
				//step 5:logout
				driver.quit();
				
				/*Actions ac=new Actions(driver);
				ac.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG')"))).perform();
				
				driver.findElement(By.linkText("Sign Out")).click();*/
		}

}


