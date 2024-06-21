package com.comcast.crm.basetest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.Homepage;
import com.comcast.crm.objectrepositoryutility.Loginpage;

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class BaseClass{
	//Base class not only provides configuration utility but also objects
	
	//Create Object 
	public DataBaseUtility dbLib=new DataBaseUtility();
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;//static variable to use in listener class

	
	
	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void configBS() throws Throwable
	{
		System.out.println("==Connect to DB,Report Config==");
		dbLib.getDbconnection();
		
		
	}
	
	
	@Parameters("BROWSER")   //import parameters from anotation
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	
	public void configBC(@Optional("chrome") String browser) throws Throwable
	{
		System.out.println("===Launch the browser===");
		//String BROWSER = browser;
				//fLib.getDataFromPropertiesFile("browser");
		String BROWSER=System.getProperty("browser",fLib.getDataFromPropertiesFile("browser"));
		
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver=driver;//temporary variable
		UtilityClassObject.setDriver(driver);
		

	}
	
	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void configBM() throws Throwable
	{
		System.out.println("==login==");
		//String URL=fLib.getDataFromPropertiesFile("url");
		//String USERNAME=fLib.getDataFromPropertiesFile("username");
		//String PASSWORD=fLib.getDataFromPropertiesFile("password");
		
		String URL=System.getProperty("url",fLib.getDataFromPropertiesFile("url"));
		String USERNAME=System.getProperty("username",fLib.getDataFromPropertiesFile("username"));
		String PASSWORD=System.getProperty("password",fLib.getDataFromPropertiesFile("password"));
		
		Loginpage lp=new Loginpage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void configAM() throws Throwable
	{
		System.out.println("==logout==");
		Homepage hp=new Homepage(driver);
		hp.logout();
		
	}
	
	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void configAC()
	{
		System.out.println("==close browser===");
		driver.quit();
		
		
	}
	
	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void configAS() throws Throwable
	{
		System.out.println("==close Db,report backup===");
		dbLib.closeDbConnection();
	}
	
	@Test(groups= {"smokeTest","regressionTest"})
	public void createContact()
	{
		System.out.println("execute create contact");
		
	}
	
	@Test(groups= {"smokeTest","regressionTest"})
	public void createContactWithDate()
	{
		System.out.println("execute create contactwithdate");
		
	}
	
	
	
	

	
}
