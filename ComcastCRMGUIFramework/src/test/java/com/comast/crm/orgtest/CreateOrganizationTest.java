package com.comast.crm.orgtest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.Homepage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.Organizationpage;
@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)

public class CreateOrganizationTest extends BaseClass {

	@Test(groups="smokeTest")
	public void createOrgTest() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		
		//read test script data from excel utility and get random num from java utility
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		
		// Step 2:navigate to organization
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		Homepage hp=new Homepage(driver);
		hp.getOrgLink().click();
	   Thread.sleep(2000);
	   
		// Step 3:click on create organization plus button
        try {
        	UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Organizationpage op=new Organizationpage(driver);
		op.getCreateNewOrgbtn().click();
		
		// Step 4:enter all details and create new organization
		UtilityClassObject.getTest().log(Status.INFO, "create a new org page");
		CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
		cnp.createOrg(orgName);
		//ListImpClass.test.log(Status.INFO+orgName+"===>create new org page");
		
		//verify Header msg Expected Result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true,actOrgName.contains(orgName));
	
	}
	
	@Test(groups="regressionTest")
	public void createOrgwithIndustries() throws Throwable
	{

		JavaUtility jLib=new JavaUtility();
		
		//read test script data from excel utility and get random num from java utility
		String orgName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();
			
		// Step 2:navigate to organization
		Homepage hp=new Homepage(driver);
		hp.getOrgLink().click();
		
        Thread.sleep(2000);
		// Step 3:click on create organization plus button
		Organizationpage op=new Organizationpage(driver);
		op.getCreateNewOrgbtn().click();
		
		
	}
}
		
		
	
	

       
	
