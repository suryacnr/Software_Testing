package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class testng_dependancy {
	WebDriver driver;
	
	@BeforeClass
	public void browserstarts() {
		System.setProperty("webdriver.chrome.driver", "/home/surya/eclipse/chromedriver");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	@Test
	public void startApp() throws Exception {
		driver.get("http://vidhvaa.com/vidhvaaindex");
		Thread.sleep(3000);
		driver.navigate().to("http://vidhvaa.com/userLogin");
		Thread.sleep(3000);
		String  currentUrl =driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("/userLogin"));
		
		//System.out.println("Starting the app");
	
	}
	@Test(dependsOnMethods = "startApp")
	public void login() throws Exception {
		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("8220228161");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("suryacnr18@gmail.com");
		driver.findElement(By.xpath("//input[@type='button']")).click();
		Thread.sleep(3000);
		boolean status = driver.findElement(By.xpath("//a[@id='navbarDropdownMenuLink']")).isDisplayed();
		Assert.assertTrue(status);
		//System.out.println("logiging to the application");
		//Assert.assertEquals(12, 13);
	
	}
	//we can add multiple dependancy
	@Test(dependsOnMethods = "login")
	public void logout() throws Exception {
		driver.findElement(By.xpath("//a[@id='navbarDropdownMenuLink']")).click();
		driver.findElement(By.xpath("//a[@class='dropdown-item']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mobile']")).isDisplayed());
		
		
		//System.out.println("logiging out of application");
	
	}
	@AfterClass
	public void closebrowser() {
		driver.quit();
	}

}
