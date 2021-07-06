package com.selenium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import library.utility;

public class take_screenshot {
	@Test
	public void screenshot() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/surya/eclipse/chromedriver");
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.seleniumeasy.com/test/");
		/*//you cannot create object as interface
		TakesScreenshot ts =(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		//you have to take screenshot in same project
		FileHandler.copy(src, new File("./screenshots/sample.png"));
		System.out.println("screenshot taken");*/
		
		utility.screenshot(driver, "same");
		
		
	}

}
