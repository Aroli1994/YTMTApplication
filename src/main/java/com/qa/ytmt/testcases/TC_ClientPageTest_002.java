package com.qa.ytmt.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.ytmt.pageobjects.ClientPage;
import com.qa.ytmt.pageobjects.LoginPage;

public class TC_ClientPageTest_002 extends BaseClass {
	//LoginPage lp = new LoginPage(driver);
	//ClientPage cp = new ClientPage(driver);
	LoginPage lp;
	ClientPage cp;
	
	@Test(priority = 1)
	public void createClient() {
		lp = new LoginPage(driver);
		lp.setEmailAddress(username);
		logger.info("username provided");
		lp.setPassword(password);
		logger.info("password provided");
		lp.clickLogin();

		cp = new ClientPage(driver);
		cp.clickAdministration();
		cp.clickProjects();
		cp.clickClients();
		cp.clickNew();
		cp.setClientName("Client750");
		cp.setContactName("StevenSmith");
		cp.setCountry("Australia");
		cp.clickSave();
		System.out.println(cp.getMessage());
		logger.info(cp.getMessage());
		cp.clickBack();

	}

	@Test(priority = 2, enabled = false)
	public void verifyClientFromTable() {
		lp.setEmailAddress(username);
		lp.setPassword(password);
		lp.clickLogin();
		
		cp.clickAdministration();
		cp.clickProjects();
		cp.clickClients();
		System.out.println(cp.verifyClientInClientTable("Client750"));
		if (cp.verifyClientInClientTable("Client750")) {
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "ClientVerification1");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3, enabled = false)
	public void verifyClientUsingSearchTest() {
		System.out.println(cp.searchClient("Client750"));
		if (cp.searchClient("Client750")) {
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "ClientVerification2");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 4)
	public void printOnlyClientFromTable() {
		cp.printClientsFromClientTable();
	}

	@Test(priority = 5)
	public void printDetailsOfAllClient() {
		cp.printAllCellValuesFromClientTable();
	}

}
