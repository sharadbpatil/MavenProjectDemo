package com.MavenProjectDemo.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.MavenProjectDemo.pageObject.LoginPage;
import com.MavenProjectDemo.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	@Test(dataProvider="logindata")
	public void loginDDT(String user,String pass) throws InterruptedException, IOException 
	{
		driver.get(appUrl);
		logger.info("URL Open");
		LoginPage lp =new LoginPage(driver);
		
		lp.setUserName(user);
		logger.info("User provided");
		
		lp.setPassword(pass);
		logger.info("Password provided");
		
		lp.clickLogin();
		logger.info("Login button clicked");
		Thread.sleep(3000);
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration")) {
			Assert.assertTrue(true);
			logger.info("Login success");
			System.out.println("Login success");
			lp.clickLogout();
		}
		else {
			System.out.println("Login Fail");
			logger.info("Login fail");
			//getScreenshot(driver,"TC_LoginTest_001"); //To capture screenshot if test fail
			Assert.assertTrue(false);
		}
		
	}
	@DataProvider(name="logindata")
	@Test
	public static String[][] loginData() throws IOException
	{
		String xlpath=System.getProperty("user.dir")+"/src/test/java/com/MavenProjectDemo/testData/logindataExcel.xlsx";
		int rows=XLUtils.getRowCount(xlpath, "Sheet1");
		int cols=XLUtils.getCellCount(xlpath,"Sheet1",1);
		String logindataArray[][] = new String[rows][cols];
		for(int i=1;i<=rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				logindataArray[i-1][j]=XLUtils.getCellData(xlpath, "Sheet1",i,j);
			}
		}
		return logindataArray;
		
	}
	
}
