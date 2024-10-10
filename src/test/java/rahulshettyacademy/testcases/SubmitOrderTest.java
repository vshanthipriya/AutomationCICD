package rahulshettyacademy.testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
 

	String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups="Purchase")
	public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException{
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match= cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
	
	JavascriptExecutor js= (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,1000)");
	Thread.sleep(2000);
	//w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.totalRow button"))).click();
	//driver.findElement(By.cssSelector("li.totalRow button")).click();
	//cartPage.goToCartPage();
	CheckoutPage checkoutPage= cartPage.goToCheckout();
	checkoutPage.selectCountry("india");
	ConfirmationPage confirmationPage= checkoutPage.submitOrder();
	
	
	
	String confirmMessage= confirmationPage.getConfirmationMessage();
	
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	
	
	}
	@Test(dependsOnMethods={"submitOrder"})
	public void orderHistoryTest() {
		
		ProductCatalogue productCatalogue=landingPage.loginApplication("priya@xyz.com", "Shanthi@12");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>>data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}
	
	/*HashMap<String,String>map=new HashMap<String,String>();
	map.put("email", "priya@xyz.com");
	map.put("password", "Shanthi@12");
	map.put("product", "ZARA COAT 3");
	
	HashMap<String,String>map1=new HashMap<String,String>();
	map1.put("email", "priya@xyz.com");
	map1.put("password", "Shanthi@12");
	map1.put("product", "ADIDAS ORIGINAL");*/

}
