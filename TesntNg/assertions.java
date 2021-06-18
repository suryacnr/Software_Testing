package testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assert_softAssert {
	//hard assert
	@Test
	public void assertion()
	{
		Assert.assertEquals(12, 13 ,"Drop down does not match please check");
		
	}
	@Test
	public void Assertion_true()
	{
		String condtion = "surya narayanan";
		Assert.assertTrue(condtion.contains("surya"), "names are not matching");
	}
	//soft assert
	@Test
	public void softassert()
	{
		SoftAssert assertion = new SoftAssert();
		assertion.assertEquals(12, 13);
		assertion.assertAll();
		
	}
	

}
