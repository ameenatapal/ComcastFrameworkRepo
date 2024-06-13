package practicetest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class InvoiceTest extends BaseClass {
	@Test(retryAnalyzer=com.comcast.crm.listenerutility.RetryListenerImplement.class)
	public void activateSim()
	{
		System.out.println("execute createInvoicetest");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle,"Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}


}
