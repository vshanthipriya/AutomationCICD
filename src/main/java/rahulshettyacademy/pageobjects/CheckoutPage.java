package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/*@FindBy(xpath="//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;*/
	
	@FindBy(css="section[class*='ta-results'] button:nth-child(3)")
	WebElement selectCountry;
	
	/*@FindBy(css="a.bnn.action__submit.ng-star-insertedt")
	WebElement submit;*/
	
	@FindBy(css="a[class*='btnn']")
	WebElement submit;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	
	By results= By.cssSelector("section.ta-results");
	
			
	
	public void selectCountry(String countryName) {
		
		Actions a=new Actions(driver);
		
		a.sendKeys(country,countryName).build().perform();
		
		waitForElementToAppear(results);
		
		selectCountry.click();
		
		
	}
	public ConfirmationPage submitOrder() {
		
		submit.click();
		
		return new ConfirmationPage(driver);
	}
	
}
