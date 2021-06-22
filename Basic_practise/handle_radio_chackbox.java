package com.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class handle_radio_checkbox {
	@Test
	public void handle_radio() {
		System.setProperty("webdriver.chrome.driver", "/home/surya/eclipse/chromedriver");
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
		//we are using findelements coz of multiple elements in radio
		//we are using name ageGroup coz to get comman value of radio button
		
				
				
		  List<WebElement> radios =driver.findElements(By.xpath("//input[@name='optradio' and @type='radio']"));
		  for(int i =0; i < radios.size(); i++) {
			  WebElement local_radios=radios.get(i);
			  String value =local_radios.getAttribute("value");
			  if(value.equalsIgnoreCase("Female")) {
				  local_radios.click();
				  break;
			  }
		  }
		  List<WebElement> radio =driver.findElements(By.xpath("//input[@name='ageGroup' and @type='radio']"));
			for(int i=0; i<radio.size(); i++) {
				//since we are using list so we are using get
				WebElement local_radio = radio.get(i);
				String value= local_radio.getAttribute("value");
				System.out.println(value);
				if(value.equalsIgnoreCase("0 - 5")) {
					local_radio.click();
					break;
				}
			}
			
		
		
		
		
		
		
	}

}
