package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage2 {

	WebDriver driver = null;
	By username_textbox = By.name("username");
	By password_textbox = By.name("password");
	By login_button = By.name("login-btn");
	
	public LoginPage2(WebDriver driver) {
		this.driver = driver;
	}
	public void url_login() {
		driver.navigate().to("http://localhost:3001/");
	}
	
	public void setTextInUsername(String text) {
		driver.findElement(username_textbox).sendKeys(text);
	}
	public void setTextInPassword() {
		driver.findElement(password_textbox).sendKeys("Shelly2022!");
	}
	
	public void clickLoginButton() {
		driver.findElement(login_button).click();
	}
	
}
