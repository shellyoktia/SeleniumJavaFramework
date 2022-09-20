package Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG {
	WebDriver driver = null;
	
	@BeforeTest
	public void setUpTest() {
		//System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@Test
	public void firstTest() {
		driver.get("https://google.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		boolean aa = driver.findElement(By.xpath("//span[contains(text(),'Sign up')]")).isDisplayed();
//		assertFalse(aa, "not displayed");
//		assertTrue(aa,"displaed");
		assertEquals(4, 4);
		
	}
	@Test
	public void secondTest() {
		assertEquals(4, 5);
		
	}
	
	@Test
	public void thirdTest() {
		driver.findElement(By.xpath("//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]")).sendKeys("hello");	
	}
	
	@AfterTest
	public void tearDownTest() {
		driver.close();
	}

}
