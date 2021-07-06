package library;

import java.io.File;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class utility {
	
	
	public static void screenshot(WebDriver driver , String screenshotName) {
		//you cannot create object as interface
		try {
				TakesScreenshot ts =(TakesScreenshot)driver;
				File src = ts.getScreenshotAs(OutputType.FILE);
				//you have to take screenshot in same project
	    	    FileHandler.copy(src, new File("./screenshots/"+screenshotName+".png"));
		        System.out.println("screenshot taken");
		} catch (Exception e) {
			System.out.println("exception while taking screenshots"+e.getMessage());
		}
	}

}
