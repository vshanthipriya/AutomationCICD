package rahulshettyacademy.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartHeader;

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(2));
		
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement e) {
		
	WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(2));
		
		w.until(ExpectedConditions.visibilityOf(e));
	}
	
	public CartPage goToCartPage() {
		
		cartHeader.click();

		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		
		orderHeader.click();
		
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToDisappear(WebElement  ele) throws InterruptedException {
		Thread.sleep(2000);
		/*WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(2));
		w.until(ExpectedConditions.invisibilityOf(ele));*/
	}
	
}