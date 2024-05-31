package com.ecom.baseClass;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.amazon.utilities.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetUpBrowser 
{
	public static WebDriver driver;
	@BeforeClass
	public void setupBrowser() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions option= new ChromeOptions();
		option.setBinary("C://Users//Ankush Mhala//Desktop//SeleniumFiles//chrome-win64//chrome-win64//chrome.exe");
		driver = new ChromeDriver(option);
		driver.get(Utility.configReader("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}	

	@AfterClass
	public void tearDown() throws IOException
	{
		driver.close();
	}

}
