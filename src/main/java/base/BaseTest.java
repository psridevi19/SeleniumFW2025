package base;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.qameta.allure.Allure;
import utils.AllureReportManager;
import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {

	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
	
	@BeforeSuite
	public void setupReport() {
	
		extent = ExtentReportManager.getReportInstance();
	}
	
	@AfterSuite
	public void teardownReport()
	{
		extent.flush();
	}
	
	@BeforeMethod
	public void setup()
	{
		
		Log.info("Starting Webdrider");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("Navigating to URL...");
		driver.get("https://admin-demo.nopcommerce.com/login");
				
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginFailure");
			test.fail("Test failed... Check Screenshot",MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		
			Allure.addAttachment("Adding Invalid Credentials", AllureReportManager.getScreenshotAsFileInputStream(driver,"LoginInput"));
			
		}
		
		if(driver!=null)
		{
			Log.info("Closing browser");
			driver.quit();
		}
	}
}
