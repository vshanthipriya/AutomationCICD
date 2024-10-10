package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css="li.totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement>cartProducts;
	
	
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match= cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		
		checkoutEle.click();
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		return checkoutPage;
	}

}
