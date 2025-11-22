package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;

public class AllureReportManager {

	
	public static FileInputStream getScreenshotAsFileInputStream(WebDriver driver,String screenshotName)
	{
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			return new FileInputStream(src);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
