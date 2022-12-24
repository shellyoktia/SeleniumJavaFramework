package Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage2;


public class login_page_2 {
	
	private static WebDriver driver = null;
	
	public static void main(String[] args) {
		loginpage();
	}
	
	public static void loginpage() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	
		LoginPage2 login = new LoginPage2(driver);
		login.url_login();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		login.setTextInUsername("devshelly");
		login.setTextInPassword();
		login.clickLoginButton();
	}
	
}
