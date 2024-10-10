package rahulshettyacademy.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;
import rahulshettyacademy.testcomponents.Retry;

public class ErrorValidationsTest extends BaseTest {
 
	@Test(groups= {"errorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException{
		
		
		landingPage.loginApplication("priya@xyz.com", "Shanthi@1");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	public void productErrorValidation() throws InterruptedException {
		
		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("priya@xyz.com", "Shanthi@12");
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match= cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
}