package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Angkutan {
	WebDriver driver = null;
	private WebDriverWait wait;
	private Actions actions;
	By angkutan_purwantoro = By.xpath("//div[contains(text(),'Fuso - Purwantoro')]"); 
	
	public Angkutan(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	public void clickAngkutan_Purwantoro() {
		driver.findElement(angkutan_purwantoro).click(); 
	}
	

}
