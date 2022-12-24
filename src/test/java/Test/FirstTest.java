package Test;

//import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest {
	public static void main(String[] args) {
		//System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to("http://google.com");
//		driver.findElement(By.name("q")).sendKeys("monsta x");
		driver.findElement(By.cssSelector("center:nth-child(1) > .RNmpXc")).click();
//		WebElement beruntung = driver.findElement(By.name("btnI"));
//		beruntung.sendKeys(Keys.ENTER);
		
		
		driver.close();
	}

}
