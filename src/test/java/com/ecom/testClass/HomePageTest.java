package com.ecom.testClass;

import org.testng.annotations.Test;
import com.ecom.baseClass.SetUpBrowser;
import com.ecom.pageObject.HomePage;
public class HomePageTest extends SetUpBrowser
{
	HomePage homePage = new HomePage(driver);
//	@Test(priority = 1)
//	public void VerifySerchBox() throws InterruptedException 
//	{   
//		
//		homePage.VerifySearchBox();
//	}
	@Test(priority = 0)
	public void VerifyRibben() throws InterruptedException 
	{ 
		homePage = new HomePage(driver);
		homePage.VerifyAllRibbenElement();
	}
}
