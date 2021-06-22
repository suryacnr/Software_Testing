package com.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Drop_down {
	WebDriver driver;
	@Test
	//traditional droupdown
	public void selectDDvalue() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/surya/eclipse/chromedriver");
	    driver=new ChromeDriver();
	    driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
	    driver.manage().window().maximize();
	    WebElement month_dd = driver.findElement(By.xpath("//select[@id='select-demo']"));
	    Select month = new Select(month_dd);
	   // WebElement selected= month.getFirstSelectedOption();
		 //  System.out.println("weblement selected: "+selected.getText());
	    month.selectByIndex(5);
	    Thread.sleep(3000);
	   WebElement selected1= month.getFirstSelectedOption();
	   System.out.println("weblement selected: "+selected1.getText());
	   Assert.assertEquals(selected1.getText(),"Thursday");
	   // month.selectByValue("Monday");
	    //Thread.sleep(3000);
	    //month.selectByVisibleText("Sunday");
	    //Thread.sleep(3000);
	   //to identify whether all the elements are selected or not
	   List<WebElement>month_list= month.getOptions();
	   int total_month =month_list.size();
	   System.out.println("Total_number_of_list" + total_month);
	   Assert.assertEquals(total_month, "8");
	   for (WebElement ele: month_list) {
		   String month_name=ele.getText();
		   System.out.println("month_name_are: " + month_name);
	   }
	   
	    driver.quit();
	    
	    
	    
		
	}
    
}
