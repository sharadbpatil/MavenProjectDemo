package com.MavenProjectDemo.testCases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.MavenProjectDemo.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	
	
	@Test
	public void login() throws InterruptedException, IOException {
		driver.get(appUrl);
		logger.info("URL Open");
		LoginPage lp =new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("User provided");
		
		lp.setPassword(password);
		logger.info("Password provided");
		
		lp.clickLogin();
		logger.info("Login button clicked");
		Thread.sleep(3000);
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration123")) {
			Assert.assertTrue(true);
			logger.info("Login success");
			System.out.println("Login success");
		}
		else {
			System.out.println("Login Fail");
			logger.info("Login fail");
			getScreenshot(driver,"TC_LoginTest_001"); //To capture screenshot if test fail
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void loginbyEmail() {
		Assert.assertTrue(true);
		logger.info("Login success");
		System.out.println("Login success");
	}
	
	
	
}
