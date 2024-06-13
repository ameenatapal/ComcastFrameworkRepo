package practicetest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest2 {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) {
		// capture the price of product
		// by using data provider anotation.same TC will execute 3 times with diff set
		// of data

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.in");

		// search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);

		// capture the product info
		// below we use dynamic xpath

		String x = "//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();

		System.out.println(price);// ₹71,499
		driver.close();
	}

	@DataProvider // data provider anot always return object array coz we can store any type of
					
	public Object[][] getData() throws Throwable {
		ExcelUtility eLib = new ExcelUtility();
		int rowcount = eLib.getRowcount("Product1");
		// object variable "rowcount" is created during runtime
		System.out.println(rowcount);
		Object[][] ObjAr = new Object[rowcount][2];

		// for loop to store data,loop helps to create object array
		for (int i = 0; i<rowcount; i++) {

			ObjAr[i][0] = eLib.getDataFromExcel("Product1", i + 1, 0);
			ObjAr[i][1] = eLib.getDataFromExcel("Product1", i + 1, 1);
		}
		return ObjAr;
	}

}
