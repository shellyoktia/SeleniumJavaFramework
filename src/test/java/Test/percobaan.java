package Test;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class percobaan {

	private static WebDriver driver = null;
	int ayam;
	int haha;
	int bebek;

	@BeforeTest
	public void setUpTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		//ke url
		LoginPage.url_localhost(driver);
		
		//maximize window
		driver.manage().window().maximize();
		
		//login
		LoginPage.username_textbox(driver);
		LoginPage.password_textbox(driver);
		LoginPage.button_login(driver).click();
		
		
		
	}
	@Test(priority = 1)
	public void test() {
		ayam = 10;
		bebek = 2;
		haha = 3;
		assertEquals(bebek, ayam);
		
	}
	@Test(priority = 2)
	public void test2() {
		System.out.println(ayam);
		System.out.println(bebek);
	}
	@Test(priority = 3)
	public void test3() {
		
		System.out.println(haha);
	}
	
	
	@AfterTest
	public void tearDownTest() {    
		driver.quit();
	}

}
