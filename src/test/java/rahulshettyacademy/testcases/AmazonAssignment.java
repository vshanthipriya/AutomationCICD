package rahulshettyacademy.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonAssignment {

	public static void main(String[] args) {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		
		
		
		
	List<WebElement>products= 	
			driver.findElements(By.cssSelector("div.product"));
	for(int i=0;i<products.size();i++) {
		
		System.out.println(products.get(i).getText());
	}
	
	WebElement pro =products.stream().filter(product->product.findElement(By.cssSelector("div.product h4")).getText().contains("Cauliflower - 1 Kg")).findFirst().orElse(null);
	
	//WebElement pro= products.stream().filter(p->p.getText().contains("Cucumber-1 Kg")).findFirst().orElse(null);
	
	pro.findElement(By.cssSelector("div button")).click();
	}

}
