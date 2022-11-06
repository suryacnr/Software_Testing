package testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assert_softAssert {
	//hard assert
	// hard assert means if the assertion fails all the crosponding lines will not execute
	@Test
	public void assertion()
	{
		Syso("open browser");
		Assert.assertEquals(true,faile); //if this line failes the below line will  not execute this is called hard assert
		Syso("enter the url");
		Assert.assertEquals(12, 13 ,"Drop down does not match please check");
		
	}
	@Test
	public void Assertion_true()
	{
		String condtion = "surya narayanan";
		Assert.assertTrue(condtion.contains("surya"), "names are not matching");
	}
	//soft assert
	//if we are doing some validation in the test cases and after that also there some line of code to execute then you can put soft assert
	//sofft assert will not terminate the program
	//if you put soft assert and if test case fails it will not show in the report as fail to solve problem we have to use Softassert.asserAll(); at the end of the test ccases
	@Test
	public void softassert()
	{
		Syso("open browser");
		SoftAssert.assertEquals(true,faile); //if this line failes the below line will  not execute this is called hard assert
		Syso("enter the url");
		SoftAssert assertion = new SoftAssert();
		assertion.assertEquals(12, 13  "Home page is missing");
		assertion.assertAll();//if test case fails it will not show in the report as fail to solve problem we have to use Softassert.asserAll(); at the end of the test ccases
		
	}
	
	//Dont put inside this method if you put it fail only teardownclass so you have to use befor each class
	@AfterClass
	public void tearndownclass(){
		softeassert.assertALL();
	}
	
	

}
