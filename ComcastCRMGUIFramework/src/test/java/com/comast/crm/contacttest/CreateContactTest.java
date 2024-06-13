package com.comast.crm.contacttest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.Homepage;

public class CreateContactTest extends BaseClass {
	@Test(groups="smokeTest")

	public void createContactTest() throws Throwable {

		// read test script data from excel utility and get random num from java utility
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// Step 2:navigate to contacts
		Homepage hp = new Homepage(driver);
		hp.getContactLink().click();

		//step 3:click on contact plus image
		ContactPage cp=new ContactPage(driver);
		cp.getCreateCont().click();
		

		// Step 4:enter all details and create new contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.CreateContact(lastName);

		// Verify Header msg and lastname and Expected Result

		String actHeader =cp.getHeaderMsg().getText();//expected header xpath put in object repository
		boolean status = actHeader.contains(lastName);
         Assert.assertEquals(status, true);

		String actLastName= driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actLastName,lastName);
		

		/*if (actLastName.equals(lastName))// contains function in java we can verify partial data
		{
			System.out.println(lastName + "is created==PASS");
		} else {
			System.out.println(lastName + "is  not created==FAIL");
		}*/
	}
	
	@Test(groups="regressionTest")
	public void createcontactWithsupportDateTest() throws Throwable
	{
		
		// read test script data from excel utility and get random num from java utility
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// Step 2:navigate to contacts
		Homepage hp = new Homepage(driver);
		hp.getContactLink().click();

		//step 3:click on contact plus image
		ContactPage cp=new ContactPage(driver);
		cp.getCreateCont().click();
		

		// Step 4:create new contact with support date
		CreatingNewContactPage cnp=new CreatingNewContactPage(driver);
		String startdate = jLib.getSystemDateyyyyDDMM();
		String enddate = jLib.getRequiredDateyyyyDDMM(30);
		cnp.createContactWithSupportDate(lastName, startdate, enddate);
		
		
	}

	
}
