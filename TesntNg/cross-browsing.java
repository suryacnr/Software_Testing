package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class cross_browser {
	WebDriver driver;
	@Test
	@Parameters("browser")
	public void verifytitile(String browser) {
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "/home/surya/eclipse/geckodriver");
			driver =new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/home/surya/eclipse/chromedriver");
			driver = new ChromeDriver();
		}
		else {
			System.out.println("nothing executed");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://google.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}

}
//please refer crosse_browsing.xml file
