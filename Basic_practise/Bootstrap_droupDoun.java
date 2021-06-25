package com.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Bootstrap_droupDoun {
	@Test
	public void bootstrap() {
		System.setProperty("webdriver.chrome.driver", "/home/surya/eclipse/chromedriver");
		WebDriver driver =new ChromeDriver();
		driver.get("http://seleniumpractise.blogspot.com/2016/08/bootstrap-dropdown-example-for-selenium.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@id='menu1']")).click();
		List<WebElement> dd_menu = driver.findElements(By.xpath("//ul[@class='dropdown-menu']"));
		for(int i=0; i<dd_menu.size(); i++) {
			WebElement id = dd_menu.get(i);
			//String innerhtml= id.getAttribute("innerHTML");
			String innerhtml=id.getText();
			if(innerhtml.contentEquals("JavaScript")) {
				id.click();
				break;
				
			}
			System.out.println(innerhtml);
			
			driver.quit();
		}
		
	}

}
