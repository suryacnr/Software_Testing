package com.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class alert {

	@Test
	public void handlealert() {
		
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/home/surya/eclipse/chromedriver");
		 WebDriver driver =new ChromeDriver();
		 driver.get("http://example.com");
		 driver.findElement(By.xpath("")).click(); //this is will click on alert box and pop up alerts
		 String actual_msg =  driver.switchTo().alert().getText();
		 driver.switchTo().alert().accept();// you can use this direct approch or you can use indirect approch
		 String expected_text ="plese eneter ur name";
		 Assert.assertEquals(actual_msg, expected_text);
				 
		 Alert  alt =driver.switchTo().alert();
		 alt.accept();
		 
		 

	}

}
