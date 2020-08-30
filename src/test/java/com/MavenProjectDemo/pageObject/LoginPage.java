package com.MavenProjectDemo.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

public class LoginPage {
	
	WebDriver ldriver=null;
	
	public LoginPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id="Email")
	@CacheLookup
	WebElement txtuserName;
	

	@FindBy(id="Password")
	@CacheLookup
	WebElement txtpassword;
	

	@FindBy(xpath="/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/input")
	@CacheLookup
	WebElement btnLogin;
	@FindBy(linkText="Logout")
	@CacheLookup
	WebElement lnkLogout;
	
	public void setUserName(String user) {
		txtuserName.clear();
		txtuserName.sendKeys(user);
	}
	
	public void setPassword(String pass) {
		txtpassword.clear();
		txtpassword.sendKeys(pass);
	}
	
	public void clickLogin() {
		btnLogin.click();
		
	}
	public void clickLogout() {
		lnkLogout.click();
	}
	
	
}
