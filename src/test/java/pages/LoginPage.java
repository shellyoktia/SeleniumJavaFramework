package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private static WebElement element = null;
	
	public static WebElement url_localhost(WebDriver driver) {
		driver.navigate().to("http://localhost:3001/");
		return element;
	}
	public static WebElement username_textbox(WebDriver driver) {
		driver.findElement(By.name("username")).sendKeys("devautotest");
		return element;
	}
	public static WebElement password_textbox(WebDriver driver) {
	    driver.findElement(By.name("password")).sendKeys("DevAutoTest2023!~");
		return element;
	}
	public static WebElement button_login(WebDriver driver) {
		element = driver.findElement(By.name("login-btn"));
		return element;
	}

}
