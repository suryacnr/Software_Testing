package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class testng_intro {
	//priority will tell to exeute in oder
	//description will tell about test method
	@Test(priority=1 , description="this test will check for login functionality")
	public void login()
	{
		System.out.println("login function");
		//which compers the actual result vs expected result 
		Assert.assertEquals(12, 13);
		
	}
	
	@Test(priority=2, description="this test will check for addtocard")
	public void addtocard()
	{
		System.out.println("added to card");
		
	}
	
	@Test(priority=3)
	public void checkout()
	{
		System.out.println("iteam checkout");
		
	}


}
