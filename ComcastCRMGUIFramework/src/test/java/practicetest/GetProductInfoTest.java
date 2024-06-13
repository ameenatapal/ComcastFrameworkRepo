package practicetest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GetProductInfoTest {
	
	@Test
	public void getProductInfoTest()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.in");
		
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone",Keys.ENTER);
		
		//capture the product info
		String x="(//span[text()='Apple iPhone 15 (128 GB) - Black'])[1]/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span[1]/span[1]";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);//₹71,499 
		
		
		
	}

}
