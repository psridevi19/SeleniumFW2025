package tests;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Allure;
import pages.LoginPage;
import utils.AllureReportManager;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {

	
	LoginPage loginPage;
	
	
	@Test
	
	public void testValidLogin()
	{
		Log.info("Testing valid credentials");
		test = ExtentReportManager.createTest("Login Test");
		test.info("Login Process");
		loginPage = new LoginPage(driver);
		
		test.info("Adding credentials");
		loginPage.enterUsername("admin@yourstore.com");
		test.info("Adding password");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();
		
		test.info("Verifying Page title");
		
		
		System.out.println("Page Title : "+driver.getTitle());
		
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
		
		if(driver.getTitle().equals("Just a moment..."))
		{
			test.pass("Login successful");
		}
		
	}
	
	@Test
	public void testLoginWithInvalidCredentials()
	{
		Log.info("Testing Invalid credentials");
		test = ExtentReportManager.createTest("Login Test with Invalid Credentials");
		test.info("Login Process");
		loginPage = new LoginPage(driver);
		
		test.info("Adding credentials");
		loginPage.enterUsername("admin@yourstore.com");
		test.info("Adding password");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();
		
		test.info("Verifying Page title");
		System.out.println("Page Title : "+driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Just a moment");
		if(driver.getTitle().equals("Just a moment...123"))
		{
			test.pass("Login successful");
		}
		
	}
	
	
}
