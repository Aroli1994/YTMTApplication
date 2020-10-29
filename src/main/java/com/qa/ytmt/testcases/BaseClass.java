package com.qa.ytmt.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public String baseURL="http://192.168.1.120:6090/ytm/login";
	public String username = "vinaya.ka@yethi.in";
	public String password = "Welcome@123";
	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeClass
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		
		logger=Logger.getLogger("YTMTapp");
		PropertyConfigurator.configure("Log4j.properties");
		
		driver.manage().window().maximize();
		driver.get(baseURL);
		driver.manage().deleteAllCookies();
		
		
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver,String testCaseName) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir") + "/Screenshots/" + testCaseName + ".png");
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			System.out.println("Exception is: "+e.getMessage());
		}
		System.out.println("Screenshot taken");
	}
	
	
}
