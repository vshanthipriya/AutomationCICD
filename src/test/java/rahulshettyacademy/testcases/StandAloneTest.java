package rahulshettyacademy.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName="ZARA COAT 3";
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage=new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("priya@xyz.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shanthi@12");
		driver.findElement(By.id("login")).click();
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
		
	WebElement prod=	products.stream()
		.filter(product->product.findElement(By.cssSelector("h5 b")).getText().equals(productName)).findFirst().orElse(null);
	
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	
	w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	//Thread.sleep(2000);
	
	driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	
	List<WebElement>cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
	
	Boolean match=	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	
	JavascriptExecutor js= (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,1000)");
	
	//Thread.sleep(2000);
	w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.totalRow button"))).click();
	//driver.findElement(By.cssSelector("li.totalRow button")).click();

	Actions a=new Actions(driver);
	
	a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
	
	w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
	
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
	driver.findElement(By.cssSelector("a.btnn.action__submit.ng-star-inserted")).click();
	
	String confirmMessage= driver.findElement(By.cssSelector("h1.hero-primary")).getText();
	
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order"));
	
	driver.quit();
	}

}
