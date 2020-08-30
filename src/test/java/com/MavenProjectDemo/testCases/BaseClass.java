package com.MavenProjectDemo.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.MavenProjectDemo.utilities.ReadConfig;

public class BaseClass {
	ReadConfig readConfigObj=new ReadConfig();
	public String appUrl=readConfigObj.getAppUrl();
	public String username=readConfigObj.getUserName();
	public String password=readConfigObj.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeClass
	@Parameters("browser")
	public void setUp(String br) {
		logger=Logger.getLogger("MavenProjectDemo");
		PropertyConfigurator.configure("Log4j.properties");
		if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",readConfigObj.getFirefoxpath() );
			driver=new FirefoxDriver();
		}
		else if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",readConfigObj.getChromepath() );
			driver=new ChromeDriver();	
		}
		else if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver",readConfigObj.getiepath() );
			driver=new InternetExplorerDriver();
		}
		
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void getScreenshot(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
	}
}
