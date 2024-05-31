package com.ecom.testClass;

import org.testng.annotations.Test;
import com.ecom.baseClass.SetUpBrowser;
import com.ecom.pageObject.HomePage;
public class HomePageTest extends SetUpBrowser
{
	HomePage homePage;
	@Test()
	public void VerifySerchBox() throws InterruptedException 
	{   
		homePage = new HomePage(driver);
		homePage.VerifySearchBox();
	}
}
