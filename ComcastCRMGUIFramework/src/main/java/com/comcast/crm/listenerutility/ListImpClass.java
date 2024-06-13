package com.comcast.crm.listenerutility;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener,ISuiteListener {
	public ExtentSparkReporter spark;
	public static ExtentReports report;//make static so u can use the reports in other class
	
	public static  ExtentTest test;
	
	public void onStart(ISuite suite)
	{
		System.out.println("report configuration");
		 String time=new Date().toString().replace(" ","_").replace(":","_");
		//start report config
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//add env information and create test
		 report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows-10");
		report.setSystemInfo("BROWSER","CHROME-100");
		System.out.println("OnStart");
	}
	
	public void onFinish(ISuite suite)
	{
		System.out.println("report backup");
		report.flush();

	}
	
	public void onTestStart(ITestResult result)
	{
		System.out.println("===>"+result.getMethod().getMethodName()+">===START==");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+">===STARTED===");
		}
	
	public void onTestSuccess(ITestResult result)
	{
		System.out.println("===>"+result.getMethod().getMethodName()+">===END==");
		
		test.log(Status.INFO, result.getMethod().getMethodName()+">===COMPLETED===");
	}
	
	public void onTestFailure(ITestResult result)
	{
		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
	  String filepath = ts.getScreenshotAs(OutputType.BASE64);
	 String time=new Date().toString().replace(" ","_").replace(":","_");
		test.addScreenCaptureFromPath(filepath,testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+">===FAILED===");
	}
	
	public void onTestSkipped(ITestResult result)
	{
		
	}
	
	
	
	

}
