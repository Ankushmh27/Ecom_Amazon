package com.ecom.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.amazon.utilities.Utility;

public class HomePage extends Utility
{
	public WebDriver driver;
	@FindBy(id = "twotabsearchtextbox")
	WebElement search_box;

	@FindBy(id = "nav-search-submit-button")
	WebElement Search_icon;


	@FindBy(id = "a-autoid-1-announce")
	WebElement addtocardButton;

	@FindBy(xpath ="//span[.='Redmi 12 5G Moonstone Silver 6GB RAM 128GB ROM']")
	WebElement redmi_mobile_Text;

	@FindBy(xpath ="//span[text()='12,499']//parent::span")    
	//"(//span[@class='a-price']//child::span)[1]")    
	//span[text()='12,499']//parent::span
	WebElement redmi_mobile_price;

	public HomePage(WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
	}
	public void VerifySearchBox() throws InterruptedException
	{
		if(search_box.isDisplayed()) 
		{
			System.out.println("search_box Displayed : "+search_box.isDisplayed());
			search_box.sendKeys("Mobile");
			String searchText=search_box.getAttribute("value");
			System.out.println("search_box text : "+searchText);
			Search_icon.click();
		}else
		{
			System.out.println("search_box is not working");
		}	

		ScrollIntoView(redmi_mobile_Text);

		String mobile_name=redmi_mobile_Text.getText();
		System.out.println("mobile_name : "+mobile_name);

		String price = redmi_mobile_price.getText();
		System.out.println("Price: " + price);
	}
}
