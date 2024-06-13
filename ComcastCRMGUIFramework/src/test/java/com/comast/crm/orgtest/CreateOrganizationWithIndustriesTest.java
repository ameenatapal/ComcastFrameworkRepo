package com.comast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustriesTest {

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
				Sheet sh=wb.getSheet("org");
				Row row=sh.getRow(4);
				String orgName = row.getCell(2).toString()+randomInt;
				String industry = row.getCell(3).toString();
				String type = row.getCell(4).toString();
				System.out.println(orgName);
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
				
				//Step 2:navigate to organization
				driver.findElement(By.linkText("Organizations")).click();
				
				//Step 3:click on create organization button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//Step 4:enter all details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				WebElement websele = driver.findElement(By.name("industry"));
				Select sel=new Select(websele);//create a object of select class to point webelement dropdown
				sel.selectByVisibleText(industry);
				
				WebElement websele1 = driver.findElement(By.name("accounttype"));
				Select sel1=new Select(websele1);//create again a object do it point to another webelement dropdown
				sel1.selectByVisibleText(type);
				
				
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				//Verify the industries and type info
				String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
				if(actIndustries.equals(industry))
				{
					System.out.println(industry + "information is verified==PASS");
				}else
				{
					System.out.println(industry + "information is not verified==FAIL");
				}
				
				//verify type
				String actType = driver.findElement(By.id("dtlview_Type")).getText();
				if(actType.equals(type))
				{
					System.out.println(industry + "information is verified==PASS");
				}else
				{
					System.out.println(industry + "information is not verified==FAIL");
				}
				
			
			//step 5:logout
				driver.quit();
			
	}

}
