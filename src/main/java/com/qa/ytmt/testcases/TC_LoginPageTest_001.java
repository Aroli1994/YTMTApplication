package com.qa.ytmt.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.ytmt.pageobjects.LoginPage;

public class TC_LoginPageTest_001 extends BaseClass{

	@Test
	public void loginTest() {
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress(username);
		logger.info("email entered");
		lp.setPassword(password);
		logger.info("password entered");
		lp.clickLogin();
		
		lp.explicitWaitForText(driver);
		
		if(driver.getPageSource().contains("Tenjin Test Manager")) {
			logger.info("LOGIN PASSED");
			Assert.assertTrue(true);
			//Assert.assertTrue(driver.getPageSource().contains("Tenjin Test Manager"));
			
		}else {
			logger.info("LOGIN FAILED");
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
		
		
	}
}
