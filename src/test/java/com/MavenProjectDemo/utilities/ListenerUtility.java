package com.MavenProjectDemo.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerUtility extends TestListenerAdapter {
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	 
	public void onStart(ITestContext tc){
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timestamp
		String reportName="Test-Report-"+timeStamp+".html";
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+reportName); //Specify location of report
		
		/*try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");  //load extent-config.xml file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		htmlReporter.config().setDocumentTitle("Automation Report");//Title of Report
		htmlReporter.config().setReportName("Functional Report");  //Name of Reoprt
		htmlReporter.config().setTheme(Theme.DARK);  
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("OS", "Windiows");
		extent.setSystemInfo("Tester Name", "SBP");
		extent.setSystemInfo("Browser", "Firefox");
	}
	public void onTestSuccess(ITestResult tr) {
		logger=extent.createTest(tr.getName()); //add entry for the with test name
		logger.log(Status.PASS, "Test case Pass is "+tr.getName());  //send pass info to report with greeen color
		
		
	}
	
	public  void onTestFailure(ITestResult tr) {
		logger=extent.createTest(tr.getName()); //add entry for the with test name
		logger.log(Status.FAIL, "Test case Failed is "+tr.getName());  //send pass info to report with Red color
		logger.log(Status.FAIL, "Test case Failed is "+tr.getThrowable()); // to add error/exception in report
		
		String screenshotpath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		//logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotpath));//add screenshot to report
		logger.addScreenCaptureFromPath(screenshotpath);//add screenshot to report
				
	}
	
	public void onTestSkipped(ITestResult tr) {
		logger=extent.createTest(tr.getName()); //add entry for the with test name
		logger.log(Status.SKIP, "Test case Skipped is "+tr.getName());  //send pass info to report with orange color
	}
	public void onFinish(ITestContext tc) {
		extent.flush();
	}
	

}
