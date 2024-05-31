package com.amazon.utilities;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.ecom.baseClass.SetUpBrowser;

public class Utility
{           
	public static WebDriver driver=SetUpBrowser.driver;
	public  WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
	public static SoftAssert softassert;

	public String getText(WebElement element)
	{
		String text = element.getText();
		return text;
	}

	public void sendKey(WebElement element, String string)
	{
		element.sendKeys(string);
	}

	public void clickOn(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void selectDropDownByText(WebElement dropdown, String text)
	{
		wait.until(ExpectedConditions.visibilityOf(dropdown));
		Select select= new Select(dropdown);
		select.selectByVisibleText(text);
	}

	public void selectDropDownByValue(WebElement dropdown, String value)
	{
		wait.until(ExpectedConditions.visibilityOf(dropdown));
		Select select= new Select(dropdown);
		select.selectByValue(value);
	}

	public void selectDropDownByIndex(WebElement dropdown, int number)
	{
		wait.until(ExpectedConditions.visibilityOf(dropdown));
		Select select= new Select(dropdown);
		select.selectByIndex(number);
	}

	public void windowHandle(int index) throws Exception
	{
		String parentWindow = driver.getWindowHandle();
		List<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(index));
		driver.close();
		driver.switchTo().window(parentWindow);
		//		driver.quit();
	}

	public void frameHandle(WebDriver driver,WebElement frameElement, int index) throws Exception
	{
		String parentWindow = driver.getWindowHandle();
		driver.switchTo().frame(frameElement);
		driver.switchTo().frame(index);
		driver.switchTo().window(parentWindow);
	}

	public void alertAccept() throws Exception
	{
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	public void alertDismiss() throws Exception
	{
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	public void alertGetText() throws Exception
	{
		Alert alert = driver.switchTo().alert();
		alert.getText();
	}

	public  void mouseHover(WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	public  void ScrollIntoView(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public  void selectOption(WebElement dropdown, WebElement optionToSelect)
	{
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", dropdown);
		jsExecutor.executeScript("arguments[0].click();", optionToSelect);
	}

	public static void assertEqual(String actual,String expected ,String message) throws InterruptedException
	{
		softassert = new SoftAssert();
		softassert.assertEquals(actual,expected,message);
		softassert.assertAll();
	}

	public static void assertTrue(boolean result) throws InterruptedException
	{
		softassert.assertTrue(result);
		softassert.assertAll();
	}

	public static void implicitWait(int time)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public void explicitWait(int time, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementPresent(int time, By element)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(time));
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
		}
		catch(Exception e) {
			System.out.println("some error occurred while waiting for the element" + element.toString());
		}
	}

	public void sleep(int timout) throws InterruptedException
	{
		Thread.sleep(timout);
	}

	public static String getScreenShotPath(String TestName) throws IOException, AWTException
	{
		File source =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir") + "/Screenshots/" + TestName + Utility.timeStamp()
		+ ".png";
		File file = new File(destPath);
		FileUtils.copyFile(source, file);
		return destPath;
	}
	public static String configReader(String Key) throws IOException{
		Properties prop = new Properties();
		FileInputStream myFile;
		myFile = new FileInputStream(System.getProperty("user.dir") + "./DataFiles/configuration.properties");
		prop.load(myFile);
		return prop.getProperty(Key);
	}

	public static String timeStamp()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YY HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}



