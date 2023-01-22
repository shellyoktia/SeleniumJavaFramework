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
	@Test
	public void test() {
		//String dada = "1.60";
		//Float ayam = Float.parseFloat(dada);
		//System.out.println(ayam);
		
//		BigDecimal bd = new BigDecimal(dada);
//		bd = bd.setScale(2, RoundingMode.HALF_DOWN);
//		float ayam = bd.floatValue(); 
//		System.out.println(ayam); // Prints 42.41
		
		float dada = 0f;
		BigDecimal bd = new BigDecimal(dada);
		bd = bd.setScale(2, RoundingMode.HALF_DOWN);
		Float dada1 = bd.floatValue();
		System.out.println(dada1);
		
		String bebek = "0";
		Float bebek1 = Float.parseFloat(bebek);
		System.out.println(bebek1);
		
		assertEquals(bebek1, dada);
		
		
	}
	
	
	@AfterTest
	public void tearDownTest() {    
		driver.quit();
	}

}
